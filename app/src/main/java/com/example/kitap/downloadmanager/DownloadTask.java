package com.example.kitap.downloadmanager;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.example.kitap.MainActivity;
import com.example.kitap.ui.home.books.BooksFragment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;

import javax.net.ssl.HttpsURLConnection;


public class DownloadTask extends AsyncTask<String, Integer, String> {
    private static final String TAG = "Download Task";
    String file_name;
    double file_size;

    private Context context;
    private ProgressDialog mProgressDialog;

    public DownloadTask(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {
        file_name = strings[0].substring(strings[0].lastIndexOf("/") + 1);
        //Log.d("output FileName", "doInBackground: " + file_name);
        //Log.d("output", "doInBackground: " + context.getApplicationContext().getFilesDir().getPath());

        try {
            InputStream input = null;
            OutputStream output = null;
            HttpURLConnection connection = null;
            try {
                URL url = new URL(strings[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    return "Server returned HTTP" + connection.getResponseCode() + "" +
                            connection.getResponseMessage();
                }

                int fileLenght = connection.getContentLength();
                file_size = fileLenght;

                input = connection.getInputStream();
                output = new FileOutputStream(context.getApplicationContext().getFilesDir().getPath()
                        + "/Kitap/" + file_name);

                Log.d(TAG, "File Name: " + file_name);

                byte data[] = new byte[4096];
                long total = 0;
                int count;
                while ((count = input.read(data)) != -1) {
                    if (isCancelled()) {
                        return null;
                    }
                    total += count;
                    if (fileLenght > 0) {
                        publishProgress((int) (total * 100 / fileLenght));
                    }
                    output.write(data, 0, count);
                }
            } catch (Exception e) {
                return e.toString();
            } finally {
                try {
                    if (output != null) {
                        output.close();
                    }
                    if (input != null) {
                        input.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (connection != null) {
                    connection.disconnect();
                }
            }
        } finally {

        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setTitle("Jükteu...");
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.setCancelable(true);

        mProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                Log.d(TAG, "Download Cancelled");

                File dir = new File(context.getApplicationContext().getFilesDir().getPath()
                        + "/Kitap/" + file_name);
                try {
                    dir.delete();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        mProgressDialog.show();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        mProgressDialog.setIndeterminate(false);
        mProgressDialog.setMax(100);
        mProgressDialog.setProgress(values[0]);
        //mProgressDialog.setMessage("File size: " + new DecimalFormat("##.##").format(file_size / 1000000 + "MB"));
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        mProgressDialog.dismiss();
        if (result != null) {
            Log.d(TAG, "onPostExecute: Error " + result);
        } else {
            Log.d(TAG, "onPostExecute: Downloaded ");
            BooksFragment.checkBool = true;
        }
    }
}
