## 필수 과제
### 회원가입
- 로그인과 동일한 방식으로 구현

### SoptService.kt
```kotlin
@POST("auth/signup")
    suspend fun postSignUp(
        @Body body: RequestSignUp
    ): Response<ResponseWrapper<ResponseSignUp>>
```
### Request, Response.kt
```kotlin
data class RequestSignUp(val name:String, val email:String, val password:String)

@Parcelize
data class ResponseSignUp(val id: Int) : Parcelable
```
### 회원가입 코드
```kotlin
private fun signUpNetWork() {
        val requestSignUp = RequestSignUp(
            name = binding.nameEdit.text.toString().trim(),
            email = binding.idEdit.text.toString().trim(),
            password = binding.passwordEdit.text.toString().trim()
        )

        val response = CoroutineScope(Dispatchers.IO).async {
            ServiceCreator.soptService.postSignUp(requestSignUp)
        }
        CoroutineScope(Dispatchers.Main).launch {
            response.await()
            if (response.isCompleted) {
                Toast.makeText(this@SignUpActivity, "가입이 완료되었습니다.", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@SignUpActivity, SignInActivity::class.java).apply {
                    putExtra("id", binding.idEdit.text.toString())
                    putExtra("password", binding.passwordEdit.text.toString())
                }
                setResult(RESULT_OK, intent)
                finish()
            } else {
                Toast.makeText(this@SignUpActivity, "회원가입에 실패했습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }
```

### 실행화면
<img width="223" alt="Screenshot_20220513_005006" src="https://user-images.githubusercontent.com/15981307/168116662-8fcf52ae-2951-41f4-9403-5ec9b366d033.png">

## 성장과제
### Github API 연동
---
### 각각의 Response
- Request가 없는 이유는 UserName 하나 String으로 보내서 따로 없습니닷
```kotlin
data class ResponseFollowing(// 고유한 id 값 비교용으로 받아옴
    val id:Int,
    val html_url: String,
    val avatar_url: String,
    val login: String,
)
data class ResponseRepo(val id:Int=0, val full_name:String, val name:String)
// 고유한 id 값 비교용으로 받아옴, 이거 왜 0들어가있지
```
### GithubService.kt
```kotlin
interface GithubService {

    @GET("users/{username}/following")
    suspend fun getFollowing(
        @Path("username") username: String
    ): Response<List<ResponseFollowing>>

    @GET("users/{username}/repos")
    suspend fun getRepository(
        @Path("username") username:String
    ): Response<List<ResponseRepo>>
}
```
### ServiceCreator.kt
```kotlin
private val retrofitGithub: Retrofit = Retrofit.Builder()
        .baseUrl(TaskServer.githubIp)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val githubService: GithubService = retrofitGithub.create(GithubService::class.java)
```
### ProfileViewModel.kt
```kotlin

class ProfileViewModel : ViewModel() {
    val followers = MutableLiveData<List<ResponseFollowing>>()
    val repository = MutableLiveData<List<ResponseRepo>>()
```

### ViewPagerActivity.kt -> getList() // 사용자의 이름을 intent로 받아와 그것으로 GET을 보냄, getStringExtra생략
```kotlin
private fun getList(userName: String) {
        if (userName.isNotBlank()) {
            val responseFollower = CoroutineScope(Dispatchers.IO).async {
                ServiceCreator.githubService.getFollowing(userName)
            }
            val responseRepository = CoroutineScope(Dispatchers.IO).async {
                ServiceCreator.githubService.getRepository(userName)
            }
            CoroutineScope(Dispatchers.Main).launch {
                profileViewModel.followers.value = responseFollower.await().body()
                profileViewModel.repository.value = responseRepository.await().body()
            }
        }
    }
```

### Repo, Follower Fragment 내부에서 observe(ListAdapter)
```kotlin
private fun displayFollowingList() {
        profileViewModel.followers.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }
private fun displayFollowingList() {
        profileViewModel.repository.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }
```
### 실행화면
<img width="250" alt="스크린샷 2022-05-13 오전 1 00 29" src="https://user-images.githubusercontent.com/15981307/168118680-bb58a8f3-2480-47ac-8d11-ef7877940c7e.png"><img width="250" alt="스크린샷 2022-05-13 오전 1 00 49" src="https://user-images.githubusercontent.com/15981307/168118727-bb8afc88-a0e3-427b-aacc-c48026990b0a.png">
---
## 성장과제 
---
### Wrapper 만들기
- 잘 만든지 모르겠어요...
### ResponseWrapper.kt
```kotlin
@Parcelize
data class ResponseWrapper<T>(
    @SerializedName("status")
    val status: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: @RawValue T? = null
) : Parcelable
```
### 사용 예시 SoptService.kt 내부
```kotlin
interface SoptService {
    @POST("auth/signin")
    suspend fun postLogin(
        @Body body: RequestSignIn
    ): Response<ResponseWrapper<ResponseSignIn>>   //Wrapper로 감쌌습니다

    @POST("auth/signup")
    suspend fun postSignUp(
        @Body body: RequestSignUp
    ): Response<ResponseWrapper<ResponseSignUp>>
}
```
---
## 도전과제
---
### 비동기 처리
- 앞서 본 GithubService, SoptService를 다 suspend 함수로 선언하였음.
#### 예시
```kotlin
interface GithubService {

    @GET("users/{username}/following")
    suspend fun getFollowing(
        @Path("username") username: String
    ): Response<List<ResponseFollowing>>

    @GET("users/{username}/repos")
    suspend fun getRepository(
        @Path("username") username:String
    ): Response<List<ResponseRepo>>
}
```
- 반환 타입을 Call<T> -> Response<T>으로 변경

### 비동기 처리, ViewPagerActivity.kt
```kotlin
private fun getList(userName: String) {
        if (userName.isNotBlank()) {
            val responseFollower = CoroutineScope(Dispatchers.IO).async {
                ServiceCreator.githubService.getFollowing(userName)
            }
            val responseRepository = CoroutineScope(Dispatchers.IO).async {
                ServiceCreator.githubService.getRepository(userName)
            }
            CoroutineScope(Dispatchers.Main).launch {
                profileViewModel.followers.value = responseFollower.await().body()
                profileViewModel.repository.value = responseRepository.await().body()
            }
        }
    }
```
- Callback 형식에서 변경하였음.
