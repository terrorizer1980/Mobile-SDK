package tokenpocket.pro.sdk_demo.minwallet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.tokenpocket.opensdk.base.NetTypeEnum;
import com.tokenpocket.opensdk.base.TPManager;

import java.util.List;

import tokenpocket.pro.sdk_demo.R;

/**
 * Created by duke on 2019/6/26.
 */

public class MiniWalletActivity extends Activity {

    private TextView tvAccounts;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        TPManager.getInstance().initSDK(this);
        TPManager.getInstance().setBlockChain(this, NetTypeEnum.EOS_MAINNET, "http://openapi.eos.ren");
        TPManager.getInstance().setAppPluginNode(this, "http://eosinfo.mytokenpocket.vip");
        TPManager.getInstance().setSeed(this, "123456");

        tvAccounts = findViewById(R.id.tv_accounts);

        findViewById(R.id.btn_clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> accounts = TPManager.getInstance().getAccounts(MiniWalletActivity.this);
                for(String account : accounts) {
                    TPManager.getInstance().clearAuth(MiniWalletActivity.this, account);
                }
            }
        });

        findViewById(R.id.btn_accounts).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> accounts = TPManager.getInstance().getAccounts(MiniWalletActivity.this);
                StringBuilder sb = new StringBuilder();
                for (String account : accounts) {
                    sb.append(account).append("  ");
                }
                tvAccounts.setText(accounts.toString());
            }

        });
        findViewById(R.id.btn_auth).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MiniWalletActivity.this, AuthActivity.class);
                MiniWalletActivity.this.startActivity(intent);
            }
        });
        findViewById(R.id.btn_pushtx).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MiniWalletActivity.this, PushTxActivity.class);
                MiniWalletActivity.this.startActivity(intent);
            }
        });
    }
}
