package ccpe001.familywallet;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

/**
 * Created by harithaperera on 5/10/17.
 */
public class AddMember extends Fragment {

    private ImageView qrImage;
    private TextView cautionText;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.add_member, container, false);
        init(view);
        return view;
    }

    private void init(View v) {
        String randomNo = "fgdfgdfgdfhdfgdfgdfgdfg";

        cautionText = (TextView) v.findViewById(R.id.editText10);

        qrImage = (ImageView) v.findViewById(R.id.qrCodeImage);
        MultiFormatWriter mfw = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = mfw.encode(randomNo, BarcodeFormat.QR_CODE,200, 200);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            qrImage.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }



}
