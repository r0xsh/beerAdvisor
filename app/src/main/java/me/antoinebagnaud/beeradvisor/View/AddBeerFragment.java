package me.antoinebagnaud.beeradvisor.View;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import me.antoinebagnaud.beeradvisor.AsyncData;
import me.antoinebagnaud.beeradvisor.Listener.GPSListener;
import me.antoinebagnaud.beeradvisor.Model.Beer;
import me.antoinebagnaud.beeradvisor.R;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

import static android.app.Activity.RESULT_OK;


public class AddBeerFragment extends Fragment {
    private ImageButton takePicture;
    private Button addBeerButton;
    private EditText beerName;
    private EditText beerCritic;
    private MaterialRatingBar beerRating;
    private ImageView imageView;
    private String currentPhotoPath = null;

    static final int REQUEST_TAKE_PHOTO = 1;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_add_beer, container, false);
        beerName = view.findViewById(R.id.editTextBeerName);
        beerCritic = view.findViewById(R.id.editTextBeerCritic);
        beerRating = view.findViewById(R.id.materialRatingBar);
        takePicture = view.findViewById(R.id.imageButtonTakePicture);
        takePicture.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                dispatchTakePictureIntent();
            }
        });

        imageView = view.findViewById(R.id.imageViewPreview);

        addBeerButton = view.findViewById(R.id.buttonValidate);
        addBeerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                saveBeer();
            }
        });
        return view;
    }

    public void saveBeer(){
        Beer beer = new Beer();
        beer.setName(beerName.getText().toString());
        beer.setCritic(beerCritic.getText().toString());
        beer.setRate(beerRating.getRating());
        beer.setImage(currentPhotoPath);
        if (GPSListener.getLocation() != null) {
            beer.setLat(GPSListener.getLocation().getLatitude());
            beer.setLng(GPSListener.getLocation().getLongitude());
        }
        beer.setCount(1);
        List<Beer> beerList = new ArrayList<Beer>();
        beerList.add(beer);
        AsyncData.insertBeers(getContext(),beerList);
        ((MainActivity) getActivity()).navigation.setSelectedItemId(R.id.navigation_home);
    }




    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getContext().getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
            ex.printStackTrace();
            }
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(getContext(),
                        "me.antoinebagnaud.beeradvisor.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }


    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,
                ".jpg",
                storageDir
        );

        currentPhotoPath = image.getAbsolutePath();
        return image;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            Glide.with(getActivity()).load(new File(currentPhotoPath)).into(imageView);
        }
    }


}
