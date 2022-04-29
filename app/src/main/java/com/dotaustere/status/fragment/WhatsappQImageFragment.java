package com.dotaustere.status.fragment;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

import com.dotaustere.status.R;
import com.dotaustere.status.adapter.WhatsappStatusAdapter;
import com.dotaustere.status.databinding.FragmentWhatsappImageBinding;
import com.dotaustere.status.interfaces.FileListWhatsappClickInterface;
import com.dotaustere.status.model.WhatsappStatusModel;

import java.io.File;
import java.util.ArrayList;

import static androidx.databinding.DataBindingUtil.inflate;

public class WhatsappQImageFragment extends Fragment implements FileListWhatsappClickInterface {
    FragmentWhatsappImageBinding binding;

    private File[] allfiles;
    private ArrayList<Uri> fileArrayList;
    ArrayList<WhatsappStatusModel> statusModelArrayList;
    private WhatsappStatusAdapter whatsappStatusAdapter;

    public WhatsappQImageFragment(ArrayList<Uri> fileArrayList) {
        this.fileArrayList = fileArrayList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = inflate(inflater, R.layout.fragment_whatsapp_image, container, false);
        initViews();
        return binding.getRoot();
    }

    private void initViews() {
        statusModelArrayList = new ArrayList<>();
        getData();
        binding.swiperefresh.setOnRefreshListener(() -> {
            statusModelArrayList = new ArrayList<>();
            getData();
            binding.swiperefresh.setRefreshing(false);
        });

    }

    private void getData() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q) {
            try {
                for (int i = 0; i < fileArrayList.size(); i++) {
                    WhatsappStatusModel whatsappStatusModel;
                    Uri uri = fileArrayList.get(i);
                    if (uri.toString().endsWith(".png") || uri.toString().endsWith(".jpg")) {
                        whatsappStatusModel = new WhatsappStatusModel("WhatsStatus: " + (i + 1),
                                uri,
                                new File(uri.toString()).getAbsolutePath(),
                                new File(uri.toString()).getName());
                        statusModelArrayList.add(whatsappStatusModel);
                    }
                }
                if (statusModelArrayList.size() != 0) {
                    binding.tvNoResult.setVisibility(View.GONE);
                } else {
                    binding.tvNoResult.setVisibility(View.VISIBLE);
                }
                whatsappStatusAdapter = new WhatsappStatusAdapter(getActivity(), statusModelArrayList, WhatsappQImageFragment.this);
                binding.rvFileList.setAdapter(whatsappStatusAdapter);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void getPosition(int position) {
        /*Intent inNext = new Intent(getActivity(), FullViewHomeWAActivity.class);
        inNext.putExtra("ImageDataFile", fileArrayList);
        inNext.putExtra("Position", position);
        getActivity().startActivity(inNext);*/
    }
}
