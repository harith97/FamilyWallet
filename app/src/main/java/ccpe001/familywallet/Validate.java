package ccpe001.familywallet;

import android.content.Context;
import android.text.Editable;
import android.widget.Toast;

/**
 * Created by harithaperera on 5/22/17.
 */
public class Validate {

    //kalin project wala validate function thyenawa nm daapalla meekata


    public static boolean confPassword(String newPw, String confPw,Context context) {
        if(newPw.equals(confPw))
        {
            if((!newPw.isEmpty())||(!confPw.isEmpty()))
            {
                return true;
            }
            else
            {
                Toast.makeText(context,"Password fields empty.",Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        else
        {
            Toast.makeText(context,"Password not matching.",Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    public static boolean confChangePassword(String newPw, String confPw, String eteredOldPw,String pin, Context context) {
        if (pin.equals(eteredOldPw)){
            if(newPw.equals(confPw))
            {
                if((!newPw.isEmpty())||(!confPw.isEmpty())||(!eteredOldPw.isEmpty()))
                {
                    return true;
                }
                else
                {
                    Toast.makeText(context,"Password fields empty.",Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
            else
            {
                Toast.makeText(context,"Password not matching.",Toast.LENGTH_SHORT).show();
                return false;
            }
        }else{
            Toast.makeText(context,"Old password not correct",Toast.LENGTH_SHORT).show();
            return false;
        }

    }

    public static boolean anyValidMail() {
        return false;
    }

    public static boolean fileValidate(Editable text) {
        if(!text.toString().isEmpty()){
            return true;
        }
        return false;
    }
}
