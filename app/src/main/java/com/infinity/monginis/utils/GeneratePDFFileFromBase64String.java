package com.infinity.monginis.utils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.util.Base64;
import android.widget.Toast;

import androidx.core.content.FileProvider;

import java.io.File;
import java.io.FileOutputStream;

public class GeneratePDFFileFromBase64String {

    private Context context;
    private String menuNameToCreateFolder;
    private String fileNameWithExtension;
    private String base64String;

    public GeneratePDFFileFromBase64String(Context context, String menuNameToCreateFolder, String fileNameWithExtension, String base64String) {
        this.context = context;
        this.menuNameToCreateFolder = menuNameToCreateFolder;
        this.fileNameWithExtension = fileNameWithExtension;
        this.base64String = base64String;
        generatePdfFromBase64String();
    }


    private void generatePdfFromBase64String() {
        try {
            File inputFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), CommonUtil.FOLDER_NAME + "/" + menuNameToCreateFolder + "/" + fileNameWithExtension);
            if (inputFile.exists()) {
                openPdfFile();
            } else {
                File directory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/" + CommonUtil.FOLDER_NAME + "/" + menuNameToCreateFolder + "/");
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                File createdFile = new File(directory, fileNameWithExtension);
                createdFile.createNewFile();

                byte[] pdfAsBytes = Base64.decode(base64String, 0);
                FileOutputStream fileOutputStream = new FileOutputStream(createdFile, false);
                fileOutputStream.write(pdfAsBytes);

                fileOutputStream.flush();
                fileOutputStream.close();
                openPdfFile();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void openPdfFile() {

        File fileToOpen = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                CommonUtil.FOLDER_NAME + "/" + menuNameToCreateFolder + "/" + fileNameWithExtension);

        Intent target = new Intent(Intent.ACTION_VIEW);
        target.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT > 24) {

            Uri uri = FileProvider.getUriForFile(context, context.getPackageName() + ".fileprovider", fileToOpen);
            context.grantUriPermission(context.getPackageName(), uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
            target.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            target.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            Intent intent = null;

            target.setDataAndType(uri, "application/pdf");
            intent = Intent.createChooser(target, "Open File");
            try {
                context.startActivity(intent);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(context, "No Apps can performs this action \n File download at" +
                        fileToOpen.getPath(), Toast.LENGTH_SHORT).show();
            }
        } else {
            target.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            target.setDataAndType(Uri.fromFile(fileToOpen), "application/pdf");
            target.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            Intent intent = Intent.createChooser(target, "Open File");
            try {
                context.startActivity(intent);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(context, "No Apps can perform this action \n File download at" +
                        fileToOpen.getPath(), Toast.LENGTH_SHORT).show();
            }
        }
    }

}
