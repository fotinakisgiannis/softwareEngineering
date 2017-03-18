package user.example.com.roadworks;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    TextView lblheader;
    Typeface font;
    Button btn;
    ListView lstroadWorks;
    SimpleAdapter ADAhere;

    /****************************Database Connection Variables*************************************/

    ConnectionClass connectionclass;
    String usernameS;
    String datets;
    String call, db, un, passwords;
    Connection connect;
    ResultSet rs;

    @SuppressLint("NewApi")
    private Connection CONN(String _user, String _pass, String _DB, String _server)
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conn = null;
        String ConnURL = null;
        try{
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnURL = "jdbc:jtds:sqlserver://" + _server + ";"
                    + "databaseName=" + _DB + ";user=" + _user + ";password=" + _pass + ";";
            conn = DriverManager.getConnection(ConnURL);
        }catch (SQLException se){
            Log.e("ERROR", se.getMessage());
        }catch (ClassNotFoundException e){
            Log.e("ERROR", e.getMessage());
        }catch (Exception e){
            Log.e("ERROR", e.getMessage());
        }
        return conn;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lblheader = (TextView) findViewById(R.id.lblheader);
        lstroadWorks = (ListView) findViewById(R.id.lstRoadWorks);
        btn = (Button) findViewById(R.id.btnview);

        /***********************DATABASE CONNECTION VARIABLE***************************************/
        connectionclass = new ConnectionClass();
        call = connectionclass.getIp();
        un = connectionclass.getUn();
        passwords = connectionclass.getPassword();
        db = connectionclass.getDb();
        connect = CONN(un, passwords, db, call);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //TODO Auto-generated method stub
                String querycmd = "select * from trafficNews2";
                try{
                    Statement statement = connect.createStatement();
                    rs = statement.executeQuery(querycmd);
                    List<Map<String, String>> data = null;
                    data = new ArrayList<Map<String, String>>();

                    while (rs.next())
                    {
                        Map<String, String> datanum = new HashMap<String, String>();
                        datanum.put("A", rs.getString("streetName"));
                        data.add(datanum);
                    }
                    String[] fromwhere = {"A"};
                    int[] viewswhere = {R.id.lblcountryname};
                    ADAhere = new SimpleAdapter(MainActivity.this, data, R.layout.listtemplate, fromwhere, viewswhere);
                    lstroadWorks.setAdapter(ADAhere);
                }catch (SQLException e){
                    Toast.makeText(MainActivity.this, e.getMessage().toString(), Toast.LENGTH_LONG).show();
                }
            }
        });

        lstroadWorks.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //TODO Auto-generated method stub
                HashMap<String, Object> obj = (HashMap<String, Object>) ADAhere.getItem(position);
                String VechicleId = (String) obj.get("A");
                Toast.makeText(MainActivity.this, VechicleId, Toast.LENGTH_LONG).show();
            }
        });
    }
}
