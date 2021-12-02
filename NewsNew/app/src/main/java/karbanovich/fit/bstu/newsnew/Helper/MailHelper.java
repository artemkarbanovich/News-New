package karbanovich.fit.bstu.newsnew.Helper;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.core.content.FileProvider;
import java.io.File;

public class MailHelper {

    public static void sendEmail (Context context, File file) {
        Uri fileUri = FileProvider.getUriForFile(context,context.getPackageName() + ".provider",file);
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        emailIntent.setType("vnd.android.cursor.dir/email");

        emailIntent.putExtra(Intent.EXTRA_STREAM, fileUri);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Ты должен увидеть эту новость!");

        context.startActivity(Intent.createChooser(emailIntent , "Send email..."));
    }
}
