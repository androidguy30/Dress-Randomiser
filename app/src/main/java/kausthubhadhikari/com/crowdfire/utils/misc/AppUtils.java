package kausthubhadhikari.com.crowdfire.utils.misc;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.provider.MediaStore;

import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;
import com.afollestad.materialdialogs.simplelist.MaterialSimpleListAdapter;
import com.afollestad.materialdialogs.simplelist.MaterialSimpleListItem;

import java.io.ByteArrayOutputStream;

/**
 * Created by kausthubhadhikari on 20/12/16.
 */

public class AppUtils {

    public static int dpTox(int size) {
        return (int) (size * Resources.getSystem().getDisplayMetrics().density);
    }

    public static MaterialDialog pickingDialog(Activity activity, int requestCode) {
        MaterialSimpleListAdapter adapter = new MaterialSimpleListAdapter(new MaterialSimpleListAdapter.Callback() {
            @Override
            public void onMaterialListItemSelected(MaterialDialog dialog, int index, MaterialSimpleListItem item) {

                switch (index) {
                    case 0: {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        activity.startActivityForResult(intent, requestCode);

                        break;
                    }

                    case 1: {
                        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        activity.startActivityForResult(intent, requestCode);
                        break;
                    }
                }
                dialog.dismiss();
            }
        });
        adapter.add(new MaterialSimpleListItem.Builder(activity).content("Camera").backgroundColor(Color.WHITE).build());
        adapter.add(new MaterialSimpleListItem.Builder(activity).content("Gallery").backgroundColor(Color.WHITE).build());

        return new MaterialDialog.Builder(activity)
                .theme(Theme.LIGHT)
                .title("Upload Docs")
                .adapter(adapter, null)
                .build();
    }

    public static byte[] bitmap2byte(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

}
