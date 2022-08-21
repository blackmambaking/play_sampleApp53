package com.example.sampleapp53.GeneratingQuestions;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sampleapp53.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.TextRecognizer;
import com.google.mlkit.vision.text.latin.TextRecognizerOptions;
import com.scanlibrary.ScanActivity;
import com.scanlibrary.ScanConstants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Scanning extends AppCompatActivity {
    public static int REQUEST_CODE = 99;
    TextRecognizer recognizer;
    public static TextView blockName;
    public static int preference = ScanConstants.OPEN_MEDIA;

    EditText editText;
    public static ArrayList<String> subTextBlocksWords;
    public static ArrayList<String> subTextBlocks;
    public static ArrayList<String> wholeTextBlocks;
    public static List<List<String>> listOfLists;
    FloatingActionButton floatingActionButton;
    String subBlock = "";
    int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanning);
        blockName = findViewById(R.id.captureTitle);
        wholeTextBlocks = new ArrayList<>();
        subTextBlocks = new ArrayList<>();

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        floatingActionButton = findViewById(R.id.floatingActionButton3);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SelectingKeyWords.class);
                startActivity(i);
            }
        });

        listOfLists = new ArrayList<List<String>>();
        recognizer   = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS);
        editText = findViewById(R.id.editimage);
        ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result2) {
                        if (result2.getResultCode() == Activity.RESULT_OK) {
                            // There are no request codes
                            Intent data = result2.getData();
                            Uri uri = data.getExtras().getParcelable(ScanConstants.SCANNED_RESULT);
                            Bitmap bitmap = null;
                            try {
                                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                                getContentResolver().delete(uri, null, null);
                                InputImage image = InputImage.fromBitmap(bitmap, 0);
                                Task<Text> result3 =
                                        recognizer.process(image)
                                                .addOnSuccessListener(new OnSuccessListener<Text>() {
                                                    @Override
                                                    public void onSuccess(Text visionText) {
                                                        // Task completed successfully
                                                        // ...
                                                        editText.setText(visionText.getText());
                                                        String resultText = visionText.getText();
                                                        for (Text.TextBlock block : visionText.getTextBlocks()) {
                                                            String blockText = block.getText();
                                                            Log.d("block", blockText);
                                                            Point[] blockCornerPoints = block.getCornerPoints();
                                                            Rect blockFrame = block.getBoundingBox();
                                                            for (Text.Line line : block.getLines()) {
                                                                String lineText = line.getText();
                                                                Log.d("line", lineText);
                                                                subTextBlocksWords = new ArrayList<>();
                                                                subTextBlocks.add(lineText);
                                                                Point[] lineCornerPoints = line.getCornerPoints();
                                                                Rect lineFrame = line.getBoundingBox();
                                                                for (Text.Element element : line.getElements()) {
                                                                    String elementText = element.getText();
                                                                    subTextBlocksWords.add(elementText);
                                                                    Log.d("word", elementText);
                                                                    Point[] elementCornerPoints = element.getCornerPoints();
                                                                    Rect elementFrame = element.getBoundingBox();
                                                                }
                                                                listOfLists.add(subTextBlocksWords);
                                                            }


                                                        }
                                                    }
                                                })
                                                .addOnFailureListener(
                                                        new OnFailureListener() {
                                                            @Override
                                                            public void onFailure(@NonNull Exception e) {
                                                                // Task failed with an exception
                                                                // ...
                                                            }
                                                        });


                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
        Intent intent = new Intent(this, ScanActivity.class);
        intent.putExtra(ScanConstants.OPEN_INTENT_PREFERENCE, preference);
        someActivityResultLauncher.launch(intent);


    }
}