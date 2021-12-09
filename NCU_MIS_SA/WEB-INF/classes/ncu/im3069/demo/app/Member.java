package ncu.im3069.demo.app;

import org.json.*;
import java.util.Calendar;

//TODO: Auto-generated Javadoc
/**
* <p>
* The Class Member
* Member類別（class）具有會員所需要之屬性與方法，並且儲存與會員相關之商業判斷邏輯<br>
* </p>
* 
* @author IPLab
* @version 1.0.0
* @since 1.0.0
*/
public class Member {
    
    /** id嚗�蝺刻�� */
    private int id;
    
    /** email嚗��摮隞嗡縑蝞� */
    private String email;
    
    /** name嚗�憪�� */
    private String name;
    
    /** password嚗�撖Ⅳ */
    private String password;
    
    /** login_times嚗������� */
    private int login_times;
    
    /** status嚗�銋� */
    private String status;
    
    /** mh嚗emberHelper銋隞嗉�ember������澈�瘜�igleton嚗� */
    private MemberHelper mh =  MemberHelper.getHelper();
    
    /**
     * 撖虫���nstantiates嚗�����ew嚗ember�隞�<br>
     * ��憭��verload嚗瘜�脰��迨撱箸���撱箇��鞈�����������
     *
     * @param email ���摮縑蝞�
     * @param password ��撖Ⅳ
     * @param name ��憪��
     */
    public Member(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
        update();
    }

    /**
     * 撖虫���nstantiates嚗�����ew嚗ember�隞�<br>
     * ��憭��verload嚗瘜�脰��迨撱箸�������鞈�������������閬鞈�澈瑼Ｙ揣������������蝯
     * 
     * @param id ��蝺刻��
     * @param email ���摮縑蝞�
     * @param password ��撖Ⅳ
     * @param name ��憪��
     */
    public Member(int id, String email, String password, String name) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        /** �������澈�閰脣��銋��������� */
        getLoginTimesStatus();
        /** 閮��銋� */
        calcAccName();
    }
    
    /**
     * 撖虫���nstantiates嚗�����ew嚗ember�隞�<br>
     * ��憭��verload嚗瘜�脰��迨撱箸����閰Ｘ�鞈������蝑��憓銝����隞�
     *
     * @param id ��蝺刻��
     * @param email ���摮縑蝞�
     * @param password ��撖Ⅳ
     * @param name ��憪��
     * @param login_times ��������
     * @param status the ��銋�
     */
    public Member(int id, String email, String password, String name, int login_times, String status) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.login_times = login_times;
        this.status = status;
    }
    
    /**
     * ����銋楊���
     *
     * @return the id ����蝺刻��
     */
    public int getID() {
        return this.id;
    }

    /**
     * ����銋摮隞嗡縑蝞�
     *
     * @return the email �����摮隞嗡縑蝞�
     */
    public String getEmail() {
        return this.email;
    }
    
    /**
     * ����銋���
     *
     * @return the name ����憪��
     */
    public String getName() {
        return this.name;
    }

    /**
     * ����銋�Ⅳ
     *
     * @return the password ����撖Ⅳ
     */
    public String getPassword() {
        return this.password;
    }
    
    /**
     * ����鞈������
     *
     * @return the login times ����鞈������
     */
    public int getLoginTimes() {
        return this.login_times;
    }
    
    /**
     * ����鞈���蝯
     *
     * @return the status ����蝯
     */
    public String getStatus() {
        return this.status;
    }
    
    /**
     * ����鞈��
     *
     * @return the JSON object ��SQL��銋�����������
     */
    public JSONObject update() {
        /** �撱箔��SONObject�隞亙摮�敺���� */
        JSONObject data = new JSONObject();
        /** ����鞈������銋������ */
        Calendar calendar = Calendar.getInstance();
        this.login_times = calendar.get(Calendar.MINUTE);
        /** 閮�董���撅砌�� */
        calcAccName();
        
        /** 瑼Ｘ閰脣����撌脩�鞈�澈 */
        if(this.id != 0) {
            /** ��������敺�����鞈�澈銝� */
            mh.updateLoginTimes(this);
            /** ��emberHelper�隞塚�������鞈�蔭鞈�澈銝� */
            data = mh.update(this);
        }
        
        return data;
    }
    
    /**
     * ���府����������
     *
     * @return the data ���府���銋�����蒂撠�JSONObject�隞嗅
     */
    public JSONObject getData() {
        /** ��SONObject撠府�������銋����脰����*/ 
        JSONObject jso = new JSONObject();
        jso.put("id", getID());
        jso.put("name", getName());
        jso.put("email", getEmail());
        jso.put("password", getPassword());
        jso.put("login_times", getLoginTimes());
        jso.put("status", getStatus());
        
        return jso;
    }
    
    /**
     * �����澈�銋�鞈��������蝯
     *
     */
    private void getLoginTimesStatus() {
        /** ��emberHelper�隞塚���摮鞈�澈�����������蝯 */
        JSONObject data = mh.getLoginTimesStatus(this);
        /** 撠��澈���摮府���銋�����晷�Member�隞嗡�惇�� */
        this.login_times = data.getInt("login_times");
        this.status = data.getString("status");
    }
    
    /**
     * 閮��銋�<br>
     * �������������憟��������
     */
    private void calcAccName() {
        /** 閮����������� */
        String curr_status = (this.login_times % 2 == 0) ? "����" : "憟��";
        /** 撠���蝯��晷�Member銋惇�� */
        this.status = curr_status;
        /** 瑼Ｘ閰脣����撌脩�鞈�澈嚗�����emberHelper�隞塚������������ */
        if(this.id != 0) mh.updateStatus(this, curr_status);
    }
}