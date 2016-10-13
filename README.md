# Mon-says-dinners-waiting-for-Android-Tutorial (你媽叫你去吃飯的Android教學)
這是一份我在學校上 App 程式設計課程時，所撰寫的一支 Android 各種元件範例的程式，也順便將程式內容、步驟紀錄下來，放上 Github 上供予其他人參考、學習。

# Main 首頁的製作、換頁特效
首先我們先設計好畫面，在 IDE 當中拖拖拉拉各種元件即可完成，另外本專案是使用 [Android-Bootstrap](https://github.com/Bearded-Hen/Android-Bootstrap "Android-Bootstrap") 完成的，可以去參考一下 ( 不過本專案有不少東西都是套用這框架 )。

大概畫面會如下圖一樣：

![Main 首頁的製作、換頁特效](/images/mom-main.png "Main 首頁的製作、換頁特效")

那接下來我們會需要將按鈕新增活動，讓他可以換頁到我們想要的目的地，做法有很多種，先從傳統的講起，我們需要在 MainActivity 當中的先把 Button 元件建立進來：

```Java
private Button view_Button;
```

但目前還只是空的元件，所以我們必須在 onCreate 當中指定他是某個 Button。

```Java
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    // 下面這行是將上面所建立的 Button 元件，指定為某個物件
    this.view_Button = (Button) findViewById(R.id.your_button);
    // [注意] 記得在 ActivityMain.xml 當中賦予你的 Button 一個 @+id/Id_Name
}
```

再來我們要在 Button 掛上監聽事件，讓這個 Button 被點擊之後，執行某些事情。

```Java
this.view_Button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        // 如果被點擊後，所執行的事件
    }
});
```

做完上面的事情，才只是單純賦予 Button 一個監聽事件，顯然非常不具效率，我們可以換種新方法來實踐這個功能，一樣記得在 ActivityMain.xml 新增一個 @+id/Id_Name，另外再多加新增一個 android:onClick="Function_Name"，接下來我們就可以在 MainActivity.java 當中新增：

```Java
public void Function_Name(View view) {
    // Code ...
}
```

這樣只要按鈕被點了，程式就會跑去執行 Function_Name 這個方法，當然如果同一個畫面中有許多按鈕，我們也可以把所有按鈕都套用同一個方法，然後在方法內去篩選觸發方法：

```Java
public void Function_Name(View view) {
        switch (view.getId()) {
            case R.id.Button_Id:
                // Code ...
                break;
        }
    }
```

再來我們需要有換頁的功能，就會需要用到：

```Java
// 初始化Intent物件
Intent intent = new Intent();
// 賦予目的地
intent.setClass(MainActivity.this, ToActivity.class);
// 開啟Activity
startActivity(intent);
// 換頁時，別忘記關閉當前頁面，以免浪費記憶體。
finish();
```

這樣主畫面就差不多了，那麼接下來要開始進入第一章囉！

# Ch01Activity 
![Ch01Activity TextView、ImageView](/images/mom-ch01.png "Ch01Activity TextView、ImageView")
###### (1)顯示文字在畫面上
###### (2)顯示圖形在畫面上

基本上，這是沒有程式的一個範例，就只是元件拉一拉而已，所以只要參考 xml 的配置即可。

# Ch02Activity 
![Ch02Activity EditText、ButtonClick](/images/mom-ch02.png "Ch02Activity TextView、ImageView")
###### (1)按下Submit按鈕會在下方文字方塊顯示輸入結果
###### (2)按下Clear按鈕會清除所有文字

介面拉完後，我們就需要在設置按鈕被點擊後的事件，但在那之前，我們知道我們會動到抓取及清除 EditText 的內容，以及修改 TextView 的內容，所以我們需要先建立這些物件：

```Java
EditText editText_Username = (EditText) findViewById(R.id.Ch02_editText_Username);
EditText editText_Password = (EditText) findViewById(R.id.Ch02_editText_Password);
EditText editText_PhoneNumber = (EditText) findViewById(R.id.Ch02_editText_PhoneNumber);
EditText editText_Age = (EditText) findViewById(R.id.Ch02_editText_Age);
TextView textView = (TextView) findViewById(R.id.Ch02_textView);
```

接下來，如果按下 Submit 之後，我們要抓取 EditText 的內容，然後 Show 在 TextView 上面：

```Java
// 先把 TextView 的內容清空
textView.setText("");
/// 將 Username 的內容抓下來，並且 Show 在 TextView 上
textView.setText(textView.getText() + "Username : " + editText_Username.getText() + "\n");
/// 將 Password 的內容抓下來，並且 Show 在 TextView 上
textView.setText(textView.getText() + "Password : " + editText_Password.getText() + "\n");
/// 將 PhoneNumber 的內容抓下來，並且 Show 在 TextView 上
textView.setText(textView.getText() + "PhoneNumber : " + editText_PhoneNumber.getText() + "\n");
/// 將 Age 的內容抓下來，並且 Show 在 TextView 上
textView.setText(textView.getText() + "Age : " + editText_Age.getText() + "\n");
```

如果按下 Clear 之後，我們要把 TextView 以及 EditText 的內容都清除掉：
```Java
// 把 TextView 的內容清空
textView.setText("");
// 把 Username 的內容清空
editText_Username.setText("");
// 把 Password 的內容清空
editText_Password.setText("");
// 把 PhoneNumber 的內容清空
editText_PhoneNumber.setText("");
// 把 Age 的內容清空
editText_Age.setText("");
```

這樣就把 Ch02 章節給搞定了！

# Ch03Activity 
![Ch03Activity Layout、ImageView](/images/mom-ch03.png "Ch03Activity TextView、ImageView")
###### (1)建立 LinearLayout，並在其內建立 1 個 FrameLayout、1 個 LinearLayout 與 1 個 Button
###### (2)點擊圖片會讓圖片消失
###### (3)按下 RESET 按鈕回復照片

範例要求 (1) 的部分，就只是很簡單的介面設計，主要是 (2)、(3) 的部分，我們需要將所有的圖片賦予 onClicke 事件，並且去除圖片：

```Java
public void Ch03_imageView_Click(View view) {
    // 每當圖片被點擊，就新增一個自己的物件
    ImageView imageView = (ImageView) findViewById(view.getId());
    // 並且賦予「空」的圖片
    imageView.setImageResource(0);
}
```

然而，如果 RESET 的按鈕被點擊時，我們必須復原圖片：

```Java
public void Ch03_Button_Click(View view) {
    // 新建一整串 ImageView 物件的陣列
    ImageView imageViewIdList[] = {
        (ImageView) findViewById(R.id.Ch03_imageView_Main),
        (ImageView) findViewById(R.id.Ch03_imageView_Bottom01),
        (ImageView) findViewById(R.id.Ch03_imageView_Bottom02),
        (ImageView) findViewById(R.id.Ch03_imageView_Bottom03)
    };
    // 並且循序的賦予圖片
    for (ImageView imageId : imageViewIdList) {
        imageId.setImageResource(R.mipmap.ic_launcher);
    }
}
```

