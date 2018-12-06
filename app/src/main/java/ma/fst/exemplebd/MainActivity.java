package ma.fst.exemplebd;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private void ShowMsg(String msg)
    {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }
    public void Add(View v)
    {
       String query = "insert into etudiant (code, nom) values ("+cd.getText().toString()+",'"+nm.getText().toString()+"')";
       myBase.execSQL(query);
    }

    public void Read(View v)
    {
      c=myBase.rawQuery("select * from etudiant",null);
      if (c!=null)
      {
          while (c.moveToNext())
          {
              ShowMsg(c.getString(1));
          }

      }

    }

    EditText cd, nm;
    SQLiteDatabase myBase;
    Cursor c;

    private void initDB()
    {
        myBase = this.openOrCreateDatabase("ExeDB",MODE_PRIVATE,null);
        myBase.execSQL("create table if not exists etudiant (code int, nom varchar(20))");
    }

    private void BindUI()
    {
        cd = findViewById(R.id.cd);
        nm = findViewById(R.id.nm);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BindUI();

        initDB();
    }
}
