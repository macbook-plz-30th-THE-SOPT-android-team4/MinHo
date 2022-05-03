
## 필수 과제 1-1, 1-2

### 구현한 코드를 설명하는 내용
- activity_home.xml 파일을 가져와 자기소개 부분을 더하였음
- 임의의 버튼 2개를 만들어서 실습 때 했던 FragmentContainerView에 버튼을 누를 때마다 transaction(replace)가 되게 해주었음
- 레파지토리 목록에서 보여주는 리싸이클러뷰는 LayoutManager에서 런타임(애매)시에 바꾸게 해주었음.


### 실행화면

<img width="150" alt="스크린샷 2022-04-23 오전 2 56 23" src="https://user-images.githubusercontent.com/15981307/164768926-f594c63e-9fe9-4195-8718-e4ef62773403.png"><img width="150" alt="스크린샷 2022-04-23 오전 2 56 56" src="https://user-images.githubusercontent.com/15981307/164769002-f3a13c5d-1bca-4148-bdae-fe26dd49069a.png">



## 성장 과제 2-2
### 구현한 코드를 설명하는 내용
- ItemDecoration은 사용하지 않았지만, Drawable 폴더에 테두리 선, radius적용, 그라데이션 효과 적용하였음
- 아이템 간 간격은 리스트 아이템에서 margin으로 주었음
### 실행화면
<img width="150" alt="스크린샷 2022-04-23 오전 3 01 28" src="https://user-images.githubusercontent.com/15981307/164769604-fc3c5150-bc04-4c7f-83d8-e118541c321b.png">


## 도전 과제 3-2(?) notify
### notifyDataSetChanged 문제점
#### 시간이 없어 직접 구현하진 못했습니다 ㅜㅜ 하지만 이론은 알고 있습니다!

- notifyDataSetChanged는 사실상 리스트를 싹 지우고 다시 처음부터 끝까지 객체를 하나하나 만들어 새로 렌더링하는 과정을 거치게 되는 것이나 마찬가지이다. 
- 때문에 비용이 매우 크게 발생한다. 따라서 효율적으로 동적인 리사이클러뷰를 구성하는 방법이 필요했다.

### 먼저 싹 지운다는 것을 증명해주는 RecyclerView 내부 코드를 살펴보자
```java
public final void notifyDataSetChanged() {
            mObservable.notifyChanged();
        }

public void notifyChanged() {
            // since onChanged() is implemented by the app, it could do anything, including
            // removing itself from {@link mObservers} - and that could cause problems if
            // an iterator is used on the ArrayList {@link mObservers}.
            // to avoid such problems, just march thru the list in the reverse order.
            for (int i = mObservers.size() - 1; i >= 0; i--) {
                mObservers.get(i).onChanged();
            }
        }
```
#### 보다시피 for문을 통해서 맨 뒤에서부터 첫번째까지 탐색하는 코드를 가지고 있기 때문에 비효율적이라는 것이다.

#### 만약 변화가 일어나는 데이터의 위치를 안다면 다음과 같은 메소드를 사용해라(notifyItemChanged)
```java
public final void notifyItemChanged(int position) {
            mObservable.notifyItemRangeChanged(position, 1);
        }
public void notifyItemRangeChanged(int positionStart, int itemCount) {
            notifyItemRangeChanged(positionStart, itemCount, null);
        }

public void notifyItemRangeChanged(int positionStart, int itemCount,
                @Nullable Object payload) {
            // since onItemRangeChanged() is implemented by the app, it could do anything, including
            // removing itself from {@link mObservers} - and that could cause problems if
            // an iterator is used on the ArrayList {@link mObservers}.
            // to avoid such problems, just march thru the list in the reverse order.
            for (int i = mObservers.size() - 1; i >= 0; i--) {
                mObservers.get(i).onItemRangeChanged(positionStart, itemCount, payload);
            }
        }
```

### 요즘은 데이터의 변화에 대해서 DiffUtil를 사용하여 성능을 향상시킨다.
- Eugene W. Myers’s의 차분 알고리즘을 이용함
- 특정 위치를 몰라도 된다.
- 이전 데이터 상태와 현재 데이터간의 상태 차이를 찾고 업데이트 되어야 할 목록을 반환해줍니다.
- RecyclerView 어댑터에 대한 업데이트를 알리는데 사용됩니다.
- DiffUtil.Callback 추상 클래스를 콜백 클래스로 활용
- 목록이 많으면 작업에 상당한 시간이 걸릴 수 있으므로 백그라운드 스레드에서 실행하고 DiffUtil.DiffResult를 가져와서 메인스레드(UI스레드)의 RecyclerView에 적용세요. 또한 구현 제약으로 목록의 최대 크기는 2²⁶개로 제한되어 있습니다.
```kotlin
class DiffUtilCallback(private val oldList: List<Any>, private val newList: List<Any>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]

        return if (oldItem is Person && newItem is Person) {
            oldItem.id == newItem.id
        } else {
            false
        }
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]
}

```
