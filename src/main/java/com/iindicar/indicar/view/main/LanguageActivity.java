package com.iindicar.indicar.view.main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import com.iindicar.indicar.R;
import com.iindicar.indicar.databinding.ActivityLanguageBinding;
import com.iindicar.indicar.utils.LocaleHelper;
import com.iindicar.indicar.view.BaseActivity;
import com.iindicar.indicar.view.main.splash.SplashActivity;

public class LanguageActivity extends BaseActivity<ActivityLanguageBinding> {

    String SetLang = "en";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_language;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding.setActivity(this);
    }

    public void btnLangEng() {
        SetLang = "en";
    }
    public void btnLangKor() {
        SetLang = "ko";
    }
    public void btnLangRus() {
        SetLang = "ru";
    }
    public void btnLangSpa() {
        SetLang = "es";
    }
    public void btnLangPor() {
        SetLang = "pt";
    }

    public void btnLangNext() {
        String message, posButton, negButton, title;
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        switch(SetLang) {
            case "en":
                message = "To change the language, you must restart the app.\nDo you want to restart the app?";
                posButton = "Restart";
                negButton = "Cancel";
                title = "Language Settings";
                break;
            case "ko":
                message = "언어를 변경하려면 앱을 재시작해야 합니다.\n재시작하시겠습니까?";
                posButton = "재시작";
                negButton = "취소";
                title = "언어 설정";
                break;
            case "ru":
                message = "Чтобы изменить язык, необходимо перезапустить приложение.\nХотите перезапустить приложение?";
                posButton = "Запустить снова";
                negButton = "Отменить";
                title = "Языковые настройки";
                break;
            case "es":
                message = "Para cambiar el idioma, debes reiniciar la aplicación.\n¿Quieres reiniciar la aplicación?";
                posButton = "Reiniciar";
                negButton = "Cancelar";
                title = "Configuraciones de Idioma";
                break;
            case "pt":
                message = "Para alterar o idioma, você deve reiniciar o aplicativo.\nVocê quer reiniciar o aplicativo?";
                posButton = "Reiniciar";
                negButton = "Cancelar";
                title = "Configurações de Linguagem";
                break;
            default:
                message = "To change the language, you must restart the app.\nDo you want to restart the app?";
                posButton = "Restart";
                negButton = "Cancel";
                title = "Language Settings";
                break;
        }


        dialog.setMessage(message);

        dialog.setPositiveButton(posButton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                LocaleHelper.setLocale(getApplicationContext(), SetLang);
                makeToast(SetLang);

                // 재시작
                Intent intent = new Intent(getApplicationContext(), SplashActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                ActivityCompat.finishAffinity(LanguageActivity.this);
                startActivity(intent);
            }
        });

        dialog.setNegativeButton(negButton, (dialog1, which) -> {

        });

        AlertDialog alert = dialog.create();
        alert.setIcon(R.drawable.app_icon);
        alert.setTitle(title);
        alert.show();
    }
}