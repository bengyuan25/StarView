# StarView

## Gradle

### root build.gradle

```
allprojects {
    repositories {
        jcenter()
    }
}
```

### app build.gradle

```
dependencies {
    implementation 'com.c.gaoyuan.StarView:1.0.0'
}
```
## Use
### Use in code
```Java
StarView starView = findViewById(R.id.start_view);
starView.setCheckStarCount(2);
starView.setCheckStarDrawable(R.drawable.search_result_brands_list_icon_star_y);
starView.setStarDrawable(R.drawable.search_result_brands_list_icon_star_g);
starView.setStarCount(5);
starView.setStarHorizontalSpace(50);
starView.setStarWidth(150);
starView.setStarHeight(150);
starView.refreshView();
```
### Use in XML
```XML
<com.c.gaoyuan.star_view_lib.StarView
        android:id="@+id/start_view"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:checkStarDrawable="@drawable/search_result_brands_list_icon_star_y"
        app:starCount="5"
        app:starDrawable="@drawable/search_result_brands_list_icon_star_g"
        app:starHeight="50dp"
        app:starHorizontalSpace="10dp"
        app:starWidth="50dp"/>
```
### OnClickListener
```Java
starView.setStoreItemOnClickListener(StoreItemOnClickListener);
```
## preview
![sample](https://github.com/bengyuan25/StarView/blob/master/samplePicture.jpg?raw=true)
