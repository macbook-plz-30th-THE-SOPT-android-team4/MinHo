### 7차 세미나 과제

# 필수 과제
### SharedPreferences를 사용해서 자동로그인 / 자동로그인 해제 구현하기
- PreferenceFragmentCompat 사용( 해당 ui 로직에서 체크, 체크 해제 구현할 필요 없음 알아서 수정)
### res/xml/login_preference.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<PreferenceScreen xmlns:android="http://schemas.android.com/apk/res/android">
    <PreferenceCategory android:title="환경설정">
    <CheckBoxPreference
        android:icon="@drawable/setting_black"
        android:key="AUTO_LOGIN"  // 해당 키 값으로 value는 CheckBox의 타입인 Boolean 저장
        android:title="자동로그인" />
    </PreferenceCategory>
</PreferenceScreen>
```
### mySettingFragment.kt
```kotlin
class MySettingFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.login_preference, null)
    }
}
```
### 온 보딩 이후 자동 로그인(SignInActivity.kt)
```kotlin
private fun initAutoLogin() {
        if (SOPTSharedPreference.getAutoLogin(this)) {
            val intent = Intent(this@SignInActivity, ViewPagerActivity::class.java)
            startActivity(intent)
        }
    }
```
### SOPTSharedPreference.kt
- 로그인 화면에서 자동 로그인 여부에 의해 sharedPreference 값은 직접 바꿔야하기 때문에 파일 수정
```kotlin

object SOPTSharedPreference {
    private const val AUTO_LOGIN = "AUTO_LOGIN"

    fun getAutoLogin(context: Context): Boolean {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(AUTO_LOGIN, false)
    }

    fun setAutoLogin(context: Context, value: Boolean) {
        PreferenceManager.getDefaultSharedPreferences(context).edit()
            .putBoolean(AUTO_LOGIN, value)
            .apply()
    }
}
```
### SignInActivity.kt
- 따라서 로그인 화면에서 자동 로그인 체크 여부에 따라서 SignIn API 호출 후 정상적일 때 SharedPreference 값 수정
```kotlin
val response = CoroutineScope(Dispatchers.IO).async {
            ServiceCreator.soptService.postLogin(requestSignIn)
        }
        CoroutineScope(Dispatchers.Main).launch {
            val responseBody = response.await()
            if (responseBody.isSuccessful) {
                Toast.makeText(
                    this@SignInActivity,
                    "${responseBody.body()?.data?.email}님 반갑습니다. ",
                    Toast.LENGTH_SHORT
                ).show()
                val intent = Intent(this@SignInActivity, ViewPagerActivity::class.java).apply {
                    putExtra("username", responseBody.body()?.data?.email)
                }
                SOPTSharedPreference.setAutoLogin(applicationContext, binding.cbAutoLogin.isChecked)
                startActivity(intent)
            } else {
                Toast.makeText(this@SignInActivity, "로그인에 실패하였습니다.", Toast.LENGTH_SHORT).show()
            }
        }
```
## 실행화면(도전 과제 영상 확인해주세요!)
<img width="250" alt="스크린샷 2022-06-02 오전 4 52 59" src="https://user-images.githubusercontent.com/15981307/171489996-0b10fd6a-76cd-4450-aa39-28ff93de6c30.png">
<img width="250" alt="스크린샷 2022-06-02 오전 4 53 28" src="https://user-images.githubusercontent.com/15981307/171490073-7dac6c00-b410-4053-adf6-4d6a7fbbc113.png">

### Device File Explorer - data/data/com.example.soptexample/shared_prefs/preference.xml
<img width="657" alt="스크린샷 2022-06-02 오전 4 54 45" src="https://user-images.githubusercontent.com/15981307/171490259-b23ab752-2ce0-4b49-ac70-2a5b41fea8f1.png">

# 성장 과제
## 온보딩 화면 만들기

### nav_sample.xml
- Flow는 다음과 같습니다
<img width="701" alt="스크린샷 2022-06-02 오전 4 57 20" src="https://user-images.githubusercontent.com/15981307/171490636-0b7fa243-1738-4157-980e-42819a3feef2.png">

### xml 내부 설정
- popUpTo, popUpToInclusive를 이용하여 fragment 백스택 제거
```xml
<?xml version="1.0" encoding="utf-8"?>
<navigation xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/nav_sample"
    app:startDestination="@id/sampleFragment1">
    <fragment
        android:id="@+id/sampleFragment1"
        android:name="com.example.soptexampleproject.presentation.onboarding.SampleFragment1"
        android:label="fragment_sample1"
        tools:layout="@layout/fragment_sample1">
        <action
            android:id="@+id/action_sampleFragment1_to_sampleFragment2"
            app:destination="@id/sampleFragment2"
            app:popUpTo="@id/sampleFragment1"
            app:popUpToInclusive="true" />
    </fragment>
    <fragment
        android:id="@+id/sampleFragment2"
        android:name="com.example.soptexampleproject.presentation.onboarding.SampleFragment2"
        android:label="fragment_sample2"
        tools:layout="@layout/fragment_sample2">
        <action
            android:id="@+id/action_sampleFragment2_to_sampleFragment3"
            app:destination="@id/sampleFragment3"
            app:popUpTo="@id/sampleFragment2"
            app:popUpToInclusive="true" />
    </fragment>
    <fragment
        android:id="@+id/sampleFragment3"
        android:name="com.example.soptexampleproject.presentation.onboarding.SampleFragment3"
        android:label="fragment_sample3"
        tools:layout="@layout/fragment_sample3">
        <action
            android:id="@+id/action_sampleFragment3_to_signInActivity"
            app:destination="@id/signInActivity"
            app:popUpTo="@id/sampleFragment3"
            app:popUpToInclusive="true" />
    </fragment>
    <activity
        android:id="@+id/signInActivity"
        android:name="com.example.soptexampleproject.presentation.sign.screens.SignInActivity"
        android:label="activity_sign_in"
        tools:layout="@layout/activity_sign_in" />

</navigation>
```
### SampleFragement3.kt
- Fragment의 마지막 flow에서 activity 전환 시 finish 작업
- navigate 실행해주는 코드도 포함
```kotlin
class SampleFragment3 : Fragment() {
    private var _binding: FragmentSample3Binding? = null
    val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSample3Binding.inflate(inflater, container, false)
        binding.button.setOnClickListener {
            findNavController().navigate(R.id.action_sampleFragment3_to_signInActivity)
            (activity as OnBoardingActivity).finish()
        }
        return binding.root
    }
}
```

## 실행화면
- github api 잦은 호출로 지금 repo, follow 로직은 잠시 막아놓았습니다.
https://user-images.githubusercontent.com/15981307/171491455-35b4c7a1-7db9-4f7d-af7a-877c8817c9d5.mov

# 도전 과제
## Room을 사용한 자동로그인 로직 만들기

### 필수 과제와 비슷한 Flow(Preference 부분에서는 해당 로직 따로 안 만들었습니다. 현재 로그인 UI에서만 자동 로그인 체크 여부로 되게 만들어놓았습니다)

### Room Database 구현
- User.kt
```kotlin
@Entity(tableName = "user_data_base")
data class User(
    @PrimaryKey
    val id: Int? = 0,
    val userName: String, val userPassword: String, val autoLogin: Boolean
)

```
- UserDao.kt
```kotlin
@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("SELECT * FROM user_data_base WHERE id=:id")
    suspend fun getUser(id: Int): User
}
```
- UserDatabase.kt
```kotlin
@Database(entities = [User::class], version = 1)
abstract class UserDatabase : RoomDatabase() {

    abstract fun UserDAO(): UserDao
    companion object {
        @Volatile
        private var INSTANCE: UserDatabase? = null
        fun getInstance(context: Context): UserDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UserDatabase::class.java,
                        "user_data_base"
                    ).build()
                }
                return instance
            }
        }
    }
}
```
- UserRepository.kt
```kotlin
class UserRepository(private val dao: UserDao) {
    suspend fun updateUser(user: User){
        dao.updateUser(user)
    }
    suspend fun deleteUser(user: User){
        dao.deleteUser(user)
    }

    suspend fun insertUser(user: User){
        dao.insertUser(user)
    }

    suspend fun getUser(id: Int):User=dao.getUser(id)

}
```
### 자동 로그인 여부 확인 SignInActivity.kt(initAutoLogin())
```kotlin
lifecycleScope.launch {
      val user = repository.getUser(0)
      if(user != null) {
            if(user.autoLogin){
                val intent = Intent(this@SignInActivity, ViewPagerActivity::class.java)
                startActivity(intent)
            }
      }else{       //유저 없는 경우에 dummy 데이터로 0번째 유저 자동로그인 여부 false로만 둠, 유저 데이터는 아직 없음
            repository.insertUser(User(0, "","",false))
      }
}
```
### 자동 로그인 여부 반영 SignInActivity.kt(loginnetwork.kt)
```kotlin
private fun loginNetWork() {
        val requestSignIn = RequestSignIn(
            email = binding.idEdit.text.toString().trim(),
            password = binding.passwordEdit.text.toString().trim()
        )
        val response = CoroutineScope(Dispatchers.IO).async {
            ServiceCreator.soptService.postLogin(requestSignIn)
        }
        CoroutineScope(Dispatchers.Main).launch {
            val responseBody = response.await()
            if (responseBody.isSuccessful) {
                Toast.makeText(
                    this@SignInActivity,
                    "${responseBody.body()?.data?.email}님 반갑습니다. ",
                    Toast.LENGTH_SHORT
                ).show()
                val intent = Intent(this@SignInActivity, ViewPagerActivity::class.java).apply {
                    putExtra("username", responseBody.body()?.data?.email)
                }
                repository.updateUser(       //로그인이 성공한 경우에만 실제 유저 데이터로 update 시켜줌, 자동 로그인 여부는 체크 여부
                    User(
                        id = 0,
                        userName = binding.idEdit.text.toString(),
                        userPassword = binding.passwordEdit.text.toString(),
                        autoLogin = binding.cbAutoLogin.isChecked
                    )
                )
                startActivity(intent)
            } else {
                Toast.makeText(this@SignInActivity, "로그인에 실패하였습니다.", Toast.LENGTH_SHORT).show()
            }
        }
}
```
## 실행화면 ( 필수 과제와 동일하므로 하나의 영상으로 처리함 )
- 근데 다시 생각해보니깐 자동 로그인은 온보딩 안 띄워도 될 것 같네요..?
https://user-images.githubusercontent.com/15981307/171493075-38b145f4-988d-46c4-a51f-fc26884ee3b2.mov


