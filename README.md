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

# Ch01Activity - TextView、ImageView
![Ch01Activity TextView、ImageView](/images/mom-ch01.png "Ch01Activity TextView、ImageView")
###### (1)顯示文字在畫面上
###### (2)顯示圖形在畫面上

基本上，這是沒有程式的一個範例，就只是元件拉一拉而已，所以只要參考 xml 的配置即可。

# Ch02Activity - EditText、ButtonClick
![Ch02Activity EditText、ButtonClick](/images/mom-ch02.png "Ch02Activity EditText、ButtonClick")
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

# Ch03Activity - Layout、ImageView
![Ch03Activity Layout、ImageView](/images/mom-ch03.png "Ch03Activity Layout、ImageView")
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

# Ch04Activity - style.xml、Theme
![Ch04Activity style.xml、Theme](/images/mom-ch04.png "Ch04Activity style.xml、Theme")
###### (1)套用style檔案
###### (2)設定應用程式的theme

也是沒有程式的範例，不做說明。

# Ch05Activity - Touch
![Ch05Activity Touch](/images/mom-ch05.png "Ch05Activity Touch")
###### (1)使用者觸擊螢幕後會顯示觸擊狀態、觸擊點總數與觸擊點的座標

首先我們會需要一個 TextView 來顯示訊息，接下來就是程式的事情了，我們可以透過 onTouchEvent 來擷取觸碰事件：

```Java
@Override
public boolean onTouchEvent(MotionEvent event) {
    // Code ...
}
```

接下來就是偵測各種事件，並且將資訊 Show 到 TextView 上：

```Java
this.touchX = event.getX();       // 觸控的 X 軸位置
this.touchY = event.getY() - 50;  // 觸控的 Y 軸位置

// 判斷觸控動作
switch (event.getAction()) {
    // 按下
    case MotionEvent.ACTION_DOWN:
        this.textView.setText(
                "偵測到點擊動作(ACTION_DOWN)\n" +
                "X Point : " + touchX + "\n" +
                "Y Point : " + touchY + "\n"
        );
        break;

    // 拖曳移動
    case MotionEvent.ACTION_MOVE:
        textView.setText(
                "偵測到拖曳移動動作(ACTION_MOVE)\n" +
                "X Point : " + touchX + "\n" +
                "Y Point : " + touchY + "\n"
        );
        break;

    // 放開
    case MotionEvent.ACTION_UP:
        textView.setText(
                "偵測到放開動作(ACTION_UP)\n" +
                "X Point : " + touchX + "\n" +
                "Y Point : " + touchY + "\n"
        );
        break;
}

// TODO Auto-generated method stub
return super.onTouchEvent(event);
```

# Ch06Activity - GesturePerformed
![Ch06Activity GesturePerformed](/images/mom-ch06.png "Ch06Activity GesturePerformed")
###### (1)使用者的手勢會與手勢資料庫內的手勢比對，如果符合會顯示該手勢名稱與正確程度（會以分數表示）
###### (2)左滑或右滑手勢會改變內框的背景色；打勾或畫圈會改變外框的背景色

```Java
/**
 * 注意：這邊需要創建「手勢資料庫」
 * 請參考 https://goo.gl/DZh7Af
 * Page. 4-31 ~ 4-32
 */
```

首先我們要先想到，因為觸發事件會改變顏色，所以我們必須有個地方儲存要改變的顏色：

```Java
private List<Integer> colorList;
```

然後寫個方法來載入顏色：

```Java
private void initColorList() {
    this.colorList = new ArrayList<>();
    this.colorList.add(R.color.bootstrap_brand_danger);
    this.colorList.add(R.color.bootstrap_brand_info);
    this.colorList.add(R.color.bootstrap_brand_primary);
    this.colorList.add(R.color.bootstrap_brand_secondary_border);
    this.colorList.add(R.color.bootstrap_brand_secondary_text);
    this.colorList.add(R.color.bootstrap_brand_success);
    this.colorList.add(R.color.bootstrap_brand_warning);
}
```

再來 Activity 執行的時候，我們要讓顏色被載入，所以要在 onCreate 當中新增：

```Java
this.initColorList();
```

再來為了讓顏色可以前後轉換，所以我們再寫兩個方法：

```Java
private int indexPlus(int index) {
    if (index >= this.colorList.size()) {
        index = 0;
        return index;
    } else {
        index++;
        return index;
    }
}

private int indexBack(int index) {
    if (index == 0) {
        return this.colorList.size();
    } else {
        index--;
        return index;
    }
}
```

再來就是手勢庫的問題：

```Java
@Override
public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
    ArrayList predictions = this.gestureLibrary.recognize(gesture);
    if (predictions.size() > 0) {
        Prediction prediction = (Prediction) predictions.get(0);
        if (prediction.score > 1.0) {
            this.textView.setText(
                    "Activity Name : " + prediction.name + "\n" +
                            "Score : " + prediction.score + "\n"
            );
            switch (prediction.name) {
                case "LeftToRight":
                    this.X_colorIndex = this.indexPlus(this.X_colorIndex);
                    this.gestureOverlayView.setBackgroundColor(colorList.get(0));
                    break;

                case "RightToLeft":
                    this.X_colorIndex = this.indexBack(this.X_colorIndex);
                    this.gestureOverlayView.setBackgroundColor(colorList.get(this.X_colorIndex));
                    break;

                case "TopToBottom":

                    break;

                case "BottomToTop":

                    break;
            }
        }
    }
}
```

最後再 onCreate 當中，匯入我們已經創建好的手勢庫：

```Java
this.gestureOverlayView = (GestureOverlayView) findViewById(R.id.Ch06_gestureOverlayView);
this.gestureOverlayView.addOnGesturePerformedListener(this);
this.gestureLibrary = GestureLibraries.fromRawResource(this, R.raw.gestures);

if (!this.gestureLibrary.load())
    Toast.makeText(Ch06Activity.this, "Gestures load is NOT!", Toast.LENGTH_LONG).show();
else
    Toast.makeText(Ch06Activity.this, "Gestures load is OK!", Toast.LENGTH_LONG).show();
```

# Ch07Activity - WebView
![Ch07Activity WebView](/images/mom-ch07.png "Ch07Activity WebView")
###### (1)一開始載入 http://www.google.com 頁面
###### (2)按下裝置上的返回鍵會回到上一個網頁

這次的題目需要使用到網路去上網，所以我們第一個想到的就是賦予 App 擁有 Internet 的權限，所以我們必須到 AndroidManifest.xml 去新增權限：

```XML
<uses-permission android:name="android.permission.INTERNET" />
```

再來就是程式只要載入，就執行某個網頁，所以我們必須在 onCreate 當中新增：

```Java
// 要前往的網址
String myURL = "http://www.google.com/";
// 創建一個 WebView 的物件
WebView myBrowser = (WebView) findViewById(R.id.Ch07_WebView);
// 創建一個 WebSettings 的物件，用來設定一些設定
WebSettings websettings = myBrowser.getSettings();
// 啟動內置的縮放功能
websettings.setSupportZoom(true);
// 當網頁滑動時，顯示 +/- 按鈕
websettings.setBuiltInZoomControls(true);
// 賦予可以執行 JavaScript 權限
websettings.setJavaScriptEnabled(true);
// 如果你在 WebView 裡點擊超連結，那就調用 Android 自帶的瀏覽器去打開它
myBrowser.setWebViewClient(new WebViewClient());
// 載入網頁
myBrowser.loadUrl(myURL);
```

再來如果按下返回/上一頁的按鈕，就讓網頁返回上一頁：

```Java
@Override
public boolean onKeyDown(int keyCode, KeyEvent event) {
    if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
        // 調用 WebView 執行 goBack()
        webView.goBack();
        return true;
    }
    return super.onKeyDown(keyCode, event);
}
```

# Ch08Activity - RatingBar
![Ch08Activity RatingBar](/images/mom-ch08.png "Ch08Activity RatingBar")
###### (1)建立 RatingBar 元件，讓使用者以點選幾顆星星的方式來評分
###### (2)會以 Toast 訊息方塊呈現選取幾個星星

這裡的部分，我們採用最簡單的方式，在 onCreate 底下新增：

```Java
// 創立一個 RatingBar 物件
RatingBar ratingBar = (RatingBar) findViewById(R.id.Ch08_ratingBar);
// 在 RatingBar 物件上掛上一個監聽事件
ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
    // 如果被選擇了
    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        // 用 Toast 在畫面上 Show 出被選擇的星星數量
        Toast.makeText(Ch08Activity.this, "目前選擇了 " + String.valueOf(rating) + " 顆星星", Toast.LENGTH_SHORT).show();
    }
});
```

# Ch09Activity - SeekBar
![Ch09Activity SeekBar](/images/mom-ch09.png "Ch09Activity SeekBar")
###### (1)建立 SeekBar 元件，讓使用者以拖曳方式來設定文字大小
###### (2)拖曳停止時會以 Toast 訊息方塊呈現文字大小對應的值

我們一樣用最簡單的方式來達成這些功能，在 onCreate 下新增：

```Java
final TextView textView = (TextView) findViewById(R.id.Ch09_textView);
textView.setTextSize((float) 8.0);
SeekBar seekBar = (SeekBar) findViewById(R.id.Ch09_seekBar);
seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

    // 如果停止拖拉 SeekBar
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        Toast.makeText(Ch09Activity.this, String.valueOf(seekBar.getProgress()), Toast.LENGTH_SHORT).show();
    }

    // 如果開始拖拉 SeekBar
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    // 正在拖拉 SeekBar
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        // 如果數值大於 N，就設定 TextView 的字體大小為 progress
        if (progress > 8)
            textView.setTextSize((float) progress);
    }
});
```


# Ch10Activity - RadioButton、CheckBox、ToggleButton與Switch
![Ch10Activity RadioButton、CheckBox、ToggleButton與Switch](/images/mom-ch10.png "Ch10Activity RadioButton、CheckBox、ToggleButton與Switch")
###### (1)當 RadioButton、CheckBox、ToggleButton 與 Switch 等不同選項按鈕的選取狀態改變時，會取得該選項按鈕上面的文字，並顯示在下方文字方塊上


# Ch11Activity - Options Menu
![Ch11Activity Options Menu](/images/mom-ch11.png "Ch11Activity Options Menu")
###### (1)點擊右上角 Menu 按鈕會跳出 Options Menu 共 5 個選項
###### (2)長按顯示結果的文字方塊會跳出 Context Menu 共 2 個選項
###### (3)點擊 Delete 按鈕會跳出 Popup Menu 共 2 個選項


# Ch12Activity - Spinner
![Ch12Activity Spinner](/images/mom-ch12.png "Ch12Activity Spinner")
###### (1)點選任何一個 Spinner 元件都會將被選取的選項文字顯示出來 


# Ch13Activity - AutoEditText
![Ch13Activity AutoEditText](/images/mom-ch13.png "Ch13Activity AutoEditText")
###### (1)輸入「T」，應用程式會作比對，並自動將符合的提示文字以列表方式呈現，方便使用者以選取方式輸入。 
