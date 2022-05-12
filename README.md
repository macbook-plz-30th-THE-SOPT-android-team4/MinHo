## 필수 과제(폰트 적용)

### 실행화면 
- 피그마 참고해서 적용
<img width="250" alt="스크린샷 2022-05-03 오후 4 46 39" src="https://user-images.githubusercontent.com/15981307/166419601-450091ee-1196-487f-975a-526050a7297c.png"><img width="250" alt="스크린샷 2022-05-03 오후 4 47 03" src="https://user-images.githubusercontent.com/15981307/166419642-9c1de7d5-f5bd-4371-8820-a48e9cb63ce6.png">

- 사용한 폰트는 다음과 같음
<img width="297" alt="스크린샷 2022-05-03 오후 4 45 27" src="https://user-images.githubusercontent.com/15981307/166419451-197b95d3-1c6b-4f67-a40f-cf38876951f1.png">
- theme 적용
<pre>
<code>
<style name="Widget.SignButton.Style" parent="">
        <item name="android:background">@drawable/shape_bordor_radius_5</item>
        <item name="android:textAppearance">@style/Widget.SignButton.TextAppearance</item>
    </style>
    <style name="Widget.SignButton.TextAppearance" parent="">
        <item name="android:textColor">@color/white</item>
        <item name="android:fontFamily">@font/noto_bold</item>
        <item name="android:textSize">@dimen/font_size_3</item>
    </style>

    <style name="Widget.SelectButton.Style" parent="">
        <item name="android:gravity">center</item>
        <item name="android:layout_marginTop">@dimen/app_base_space</item>
        <item name="android:background">@drawable/shape_border_selector_btn_radius_5</item>
        <item name="android:textAppearance">@style/Widget.SelectButton.TextAppearance</item>
    </style>
    <style name="Widget.SelectButton.TextAppearance" parent="">
        <item name="android:textColor">@color/selector_btn_text</item>
        <item name="android:fontFamily">@font/noto_bold</item>
        <item name="android:textSize">@dimen/font_size_3</item>
    </style>
</pre>
</code>

---
## 성장 과제( 중첩 스크롤 해결 )
### 문제점
- Activity에서의 Viewpager2와 PagerFragmentList 내부의 Viewpager2와의 수평 스크롤 중첩 문제로 부모의 스크롤만 스크롤 되는 경우가 발생

### 해결방안 Google Sample 사용
```kotlin
// 더 자세한 것은 코드 내부 확인
class NestedScrollableHost : FrameLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
```
### 실행화면
<img width="297" alt="Screenshot_20220503_160539" src="https://user-images.githubusercontent.com/15981307/166419928-d553e1ea-2c7f-434a-8501-b964f60a42d4.png">

---

## 도전과제 ( Image 라이브러리 )
### 갤러리에서 이미지 선택 후 Uri 반환받아 ImageView에 띄우기
- 파일 picker UI, MIME type = image/*
- Android 4.4(API 수준 19) 이상을 실행하는 기기에서 앱은 저장소 액세스 프레임워크를 사용하여 외부 저장소 볼륨 및 클라우드 기반 저장소를 포함한 문서 제공자와 상호작용할 수 있기 때문에 따로 권한을 받지 않고 사용할 수 있는 것 같다.
<img width="200" alt="스크린샷 2022-05-03 오후 5 10 22" src="https://user-images.githubusercontent.com/15981307/166422149-5bf6c33f-c1a5-452a-beec-89e060533ae6.png">

- 따라서 다음과 같이 요청 후
```kotlin
binding.btnUpload.setOnClickListener {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
                type = "image/*"
                addCategory(Intent.CATEGORY_OPENABLE)
            }
            // Only the system receives the ACTION_OPEN_DOCUMENT, so no need to test.
            startActivityForResult(intent, REQUEST_IMAGE_OPEN)
        }
```
- Uri 타입으로 받은 데이터를 Coil을 사용해서 load해보았다.
```kotlin
override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_OPEN && resultCode == Activity.RESULT_OK) {
            val fullPhotoUri: Uri? = data?.data
            if (fullPhotoUri != null) {
                binding.imageViewCoil.load(fullPhotoUri)
            }
        }
    }
    companion object {
        const val REQUEST_IMAGE_OPEN = 1
    }
```


### 실행화면

### 첨부 전
<img width="250" alt="Screenshot_20220503_160605" src="https://user-images.githubusercontent.com/15981307/166419989-f58f972d-12fe-42b9-99cf-91e3365804ef.png">

### FilePicker UI (in Gallery)

<img width="250" alt="Screenshot_20220503_160614" src="https://user-images.githubusercontent.com/15981307/166420006-fd58c34a-ff3e-4872-a957-0e6d0a2aae0a.png">

### 선택 후 Load된 모습

<img width="250" alt="Screenshot_20220503_160551" src="https://user-images.githubusercontent.com/15981307/166419994-3cc3cecc-4b2a-4092-9ec4-5908533ef4fa.png">

