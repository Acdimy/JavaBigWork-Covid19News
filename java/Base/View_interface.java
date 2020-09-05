package Base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public interface View_interface <T extends Presenter_interface>{

    void setPresenter(T presenter);

    Context getContext();

    void startAc(Intent intent,Bundle bundle);

}
