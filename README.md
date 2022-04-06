# 1차 세미나 과제
---
## 필수 과제 1-1 (로그인 페이지 만들기)

### 구현한 코드를 설명하는 내용
- 세미나 때와 크게 달라진 내용이 없으며, 회원가입 버튼만 추가한 후 액티비티 이동 및 이름만 바꿔주었습니다.

### 이번 과제를 통해 배운 내용
- 예쁜 색 찾기가 너무 어렵다...

### 실행 화면
<img width="150" alt="스크린샷 2022-04-07 오전 3 32 45" src="https://user-images.githubusercontent.com/15981307/162044308-1f6aa427-0e00-456e-a94f-d539f209d4ee.png">

---

## 필수 과제 1-2 (회원가입 페이지 만들기)

### 구현한 코드를 설명하는 내용
- 로그인 화면 xml을 다 가져오고 텍스트만 수정해주었습니다.

### 이번 과제를 통해 배운 내용
- ConstraintLayout에 익숙해지는 시간을 가졌습니다.

### 실행 화면

<img width="150" alt="스크린샷 2022-04-07 오전 3 38 15" src="https://user-images.githubusercontent.com/15981307/162045243-a677b1a5-98f2-4af5-ab8b-ec3880df8f2c.png">

---
## 필수 과제 1-3 (자기소개 페이지 만들기) + 성장 과제 2-2

### 구현한 코드를 설명하는 내용
- ScrollView 추가하였으며, 사진이 옆이 잘려서 나온거지 constraintDimensionRatio로 1:1 비율 맞춰주었습니다. 너비에 따른 높이 1:1

### 이번 과제를 통해 배운 내용
- ScrollView가 Child를 하나만 넣을 수 있다는 것을 알게되었습니다.

### 실행 화면

<img width="150" alt="스크린샷 2022-04-07 오전 3 41 08" src="https://user-images.githubusercontent.com/15981307/162045685-6ed79537-6b05-4e0d-99fd-3371723f35c5.png">

---
## 성장 과제 2-1

### 구현한 코드를 설명하는 내용

- SignInActivity.kt
``` kotlin
getResultText= registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
  if(it.resultCode== RESULT_OK){
  Log.d("data", "complete")
  binding.idEdit.text.append(it.data?.getStringExtra("id") ?:"")
  binding.passwordEdit.text.append(it.data?.getStringExtra("password") ?:"")
  }
  else{
    Log.d("data", it.toString())
    }
  }//콜백 함수를 만들어놓는다.
```
- 이후 SignUpActivity로 이동할 때 launch(intent)로 getResultText를 붙여준다.

``` kotlin
binding.signupBtn.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java )
            getResultText.launch(intent)
            //startActivity(intent)
        }
```

- SignUpActivity.kt
``` kotlin
val intent = Intent(this, SignInActivity::class.java).apply {
  putExtra("id", binding.idEdit.text.toString())
  putExtra("password", binding.passwordEdit.text.toString())
  }
setResult(RESULT_OK, intent)  //intent와 Result 값을 넣어준다.
finish()
```
### 이번 과제를 통해 배운 내용
- registerForActivityResult가 생각보다 많이 편하다는것을 알게 되었습니다.
- 회원가입시 id, password의 SigninActivity의 editText를 비워주는 것을 까먹었었다.

### 실행 화면

- 회원정보 입력
<img width="150" alt="스크린샷 2022-04-07 오전 3 48 25" src="https://user-images.githubusercontent.com/15981307/162046881-bda820c3-c404-4d86-9ffc-e96cb33d096d.png">

- 회원가입 완료 시
<img width="150" alt="스크린샷 2022-04-07 오전 3 49 25" src="https://user-images.githubusercontent.com/15981307/162047025-35e8b74a-57f8-457b-aeb6-4cb261828f4f.png">
