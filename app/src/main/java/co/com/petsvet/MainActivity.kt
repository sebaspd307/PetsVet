package co.com.petsvet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val RC_SIGN_IN = 123
    lateinit var mGoogleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gso =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        buttonLogin.setOnClickListener{
            val signInIntent = mGoogleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }

        var button1 = buttonRegistrar;
        var BotonLogin = buttonLogueo;

        BotonLogin.setOnClickListener{
            val intent = Intent(this, ServiciosActivity::class.java)
            startActivity(intent)
        }

        button1.setOnClickListener {
                val intent = Intent(this, RegistroActivity::class.java)
                startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task =
                GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            updateUI(account)
        } catch (e: ApiException) {
            //Log.w(FragmentActivity.TAG, "signInResult:failed code=" + e.statusCode)
            updateUI(null)
        }
    }

    private fun updateUI(acount: GoogleSignInAccount?) {
        if(acount != null){
            val intent = Intent(this, ServiciosActivity::class.java)
            intent.putExtra("name", acount.displayName)
            intent.putExtra("email", acount.email)
            startActivity(intent)
        }
    }

}
