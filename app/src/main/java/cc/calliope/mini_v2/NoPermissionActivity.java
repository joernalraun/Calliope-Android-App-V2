package cc.calliope.mini_v2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import cc.calliope.mini_v2.databinding.ActivityNoPermissionBinding;
import cc.calliope.mini_v2.utils.Permission;
import cc.calliope.mini_v2.utils.Version;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;

public class NoPermissionActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int REQUEST_CODE = 1022; // random number
    private ActivityNoPermissionBinding binding;
    @Permission.RequestType
    private int requestType;
    private boolean deniedForever;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityNoPermissionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.actionButton.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        requestType = getRequestType();
        deniedForever = Permission.isAccessDeniedForever(this, requestType);
        if (requestType == Permission.NOT_DEFINED) {
            finish();
        } else {
            updateUi();
        }
    }

    @Override
    public void onClick(View v) {
        if (deniedForever) {
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts("package", getPackageName(), null);
            intent.setData(uri);
            startActivity(intent);
        } else {
            String[] permissionsArray = Permission.getPermissionsArray(requestType);
            Permission.markPermissionRequested(this, requestType);
            ActivityCompat.requestPermissions(this, permissionsArray, REQUEST_CODE);
        }
    }

    private int getRequestType() {
        if (!Permission.isAccessGranted(this, Permission.BLUETOOTH)) {
            return Permission.BLUETOOTH;
        } else if (!Version.upperSnowCone && !Permission.isAccessGranted(this, Permission.LOCATION)) {
            return Permission.LOCATION;
        }
        return Permission.NOT_DEFINED;
    }

    private void updateUi() {
        ContentNoPermission content = ContentNoPermission.getContent(requestType);
        binding.iconImageView.setImageResource(content.getIcResId());
        binding.titleTextView.setText(content.getTitleResId());
        binding.messageTextView.setText(content.getMessageResId());
        binding.actionButton.setText(deniedForever ? R.string.settings_btn_permission : R.string.action_btn_permission);
    }
}