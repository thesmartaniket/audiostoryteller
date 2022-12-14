/**This project is made by Aniket Biswas
 * Linked In - https://www.linkedin.com/in/thesmartaniket/
 * */
package com.ketlot.theaudiostory;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;

public class FileUtil {

@SuppressLint("SuspiciousIndentation")
private static void createNewFile(String path) {
int lastSep = path.lastIndexOf(File.separator);
if (lastSep > 0) {
String dirPath = path.substring(0, lastSep);
makeDir(dirPath);
}

File file = new File(path);

try {
if (!file.exists())
file.createNewFile();
} catch (IOException e) {
e.printStackTrace();
}
}

    public static void copyFile(String sourcePath, String destPath) {
if (!isExistFile(sourcePath)) return;
createNewFile(destPath);

FileInputStream fis = null;
FileOutputStream fos = null;

try {
fis = new FileInputStream(sourcePath);
fos = new FileOutputStream(destPath, false);

byte[] buff = new byte[1024];
int length;

while ((length = fis.read(buff)) > 0) {
fos.write(buff, 0, length);
}
} catch (IOException e) {
e.printStackTrace();
} finally {
if (fis != null) {
try {
fis.close();
} catch (IOException e) {
e.printStackTrace();
}
}
if (fos != null) {
try {
fos.close();
} catch (IOException e) {
e.printStackTrace();
}
}
}
}

public static void moveFile(String sourcePath, String destPath) {
copyFile(sourcePath, destPath);
deleteFile(sourcePath);
}

public static void deleteFile(String path) {
File file = new File(path);

if (!file.exists()) return;

if (file.isFile()) {
file.delete();
return;
}

File[] fileArr = file.listFiles();

if (fileArr != null) {
for (File subFile : fileArr) {
if (subFile.isDirectory()) {
deleteFile(subFile.getAbsolutePath());
}

if (subFile.isFile()) {
subFile.delete();
}
}
}

file.delete();
}

public static boolean isExistFile(String path) {
File file = new File(path);
return file.exists();
}

public static void makeDir(String path) {
if (!isExistFile(path)) {
File file = new File(path);
file.mkdirs();
}
}

public static void listDir(String path, ArrayList<String> list) {
File dir = new File(path);
if (!dir.exists() || dir.isFile()) return;

File[] listFiles = dir.listFiles();
if (listFiles == null || listFiles.length <= 0) return;

if (list == null) return;
list.clear();
for (File file : listFiles) {
list.add(file.getAbsolutePath());
}
}

    public static String getPublicDir(String type) {
return Environment.getExternalStoragePublicDirectory(type).getAbsolutePath();
}

public static String convertUriToFilePath(final Context context, final Uri uri) {
String path = null;
if (DocumentsContract.isDocumentUri(context, uri)) {
if (isExternalStorageDocument(uri)) {
final String docId = DocumentsContract.getDocumentId(uri);
final String[] split = docId.split(":");
final String type = split[0];

if ("primary".equalsIgnoreCase(type)) {
path = Environment.getExternalStorageDirectory() + "/" + split[1];
}
} else if (isDownloadsDocument(uri)) {
final String id = DocumentsContract.getDocumentId(uri);

if (!TextUtils.isEmpty(id)) {
if (id.startsWith("raw:")) {
return id.replaceFirst("raw:", "");
}
}

final Uri contentUri = ContentUris
.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.parseLong(id));

path = getDataColumn(context, contentUri, null, null);
} else if (isMediaDocument(uri)) {
final String docId = DocumentsContract.getDocumentId(uri);
final String[] split = docId.split(":");
final String type = split[0];

Uri contentUri = null;
if ("image".equals(type)) {
contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
} else if ("video".equals(type)) {
contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
} else if ("audio".equals(type)) {
contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
}

final String selection = MediaStore.Audio.Media._ID + "=?";
final String[] selectionArgs = new String[]{
split[1]
};

path = getDataColumn(context, contentUri, selection, selectionArgs);
}
} else if (ContentResolver.SCHEME_CONTENT.equalsIgnoreCase(uri.getScheme())) {
path = getDataColumn(context, uri, null, null);
} else if (ContentResolver.SCHEME_FILE.equalsIgnoreCase(uri.getScheme())) {
path = uri.getPath();
}

if (path != null) {
try {
return URLDecoder.decode(path, "UTF-8");
}catch(Exception e){
return null;
}
}
return null;
}

private static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
Cursor cursor = null;

final String column = MediaStore.Images.Media.DATA;
final String[] projection = {
column
};

try {
cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
if (cursor != null && cursor.moveToFirst()) {
final int column_index = cursor.getColumnIndexOrThrow(column);
return cursor.getString(column_index);
}
} catch (Exception ignored) {

} finally {
if (cursor != null) {
cursor.close();
}
}
return null;
}


private static boolean isExternalStorageDocument(Uri uri) {
return "com.android.externalstorage.documents".equals(uri.getAuthority());
}

private static boolean isDownloadsDocument(Uri uri) {
return "com.android.providers.downloads.documents".equals(uri.getAuthority());
}

private static boolean isMediaDocument(Uri uri) {
return "com.android.providers.media.documents".equals(uri.getAuthority());
}

private static void saveBitmap(Bitmap bitmap, String destPath) {
FileOutputStream out = null;
FileUtil.createNewFile(destPath);
try {
out = new FileOutputStream(destPath);
bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
} catch (Exception e) {
e.printStackTrace();
} finally {
try {
if (out != null) {
out.close();
}
} catch (IOException e) {
e.printStackTrace();
}
}
}

public static Bitmap getScaledBitmap(String path, int max) {
Bitmap src = BitmapFactory.decodeFile(path);

int width = src.getWidth();
int height = src.getHeight();
float rate;

if (width > height) {
rate = max / (float) width;
height = (int) (height * rate);
width = max;
} else {
rate = max / (float) height;
width = (int) (width * rate);
height = max;
}

return Bitmap.createScaledBitmap(src, width, height, true);
}

public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
final int width = options.outWidth;
final int height = options.outHeight;
int inSampleSize = 1;

if (height > reqHeight || width > reqWidth) {
final int halfHeight = height / 2;
final int halfWidth = width / 2;

while ((halfHeight / inSampleSize) >= reqHeight && (halfWidth / inSampleSize) >= reqWidth) {
inSampleSize *= 2;
}
}

return inSampleSize;
}

public static Bitmap decodeSampleBitmapFromPath(String path, int reqWidth, int reqHeight) {
final BitmapFactory.Options options = new BitmapFactory.Options();
options.inJustDecodeBounds = true;
BitmapFactory.decodeFile(path, options);

options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

options.inJustDecodeBounds = false;
return BitmapFactory.decodeFile(path, options);
}

public static void resizeBitmapFileRetainRatio(String fromPath, String destPath, int max) {
if (!isExistFile(fromPath)) return;
Bitmap bitmap = getScaledBitmap(fromPath, max);
saveBitmap(bitmap, destPath);
}

}