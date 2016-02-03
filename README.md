#gdkscoreviewer#

Score Viewer for Google Glass based on the GDK

![Image 1](https://33.media.tumblr.com/63321e14078d9ee66b1346c9c5cf2fb3/tumblr_neh8jrvw3r1s77ypjo1_500.png "Score View")
![Image 2](https://33.media.tumblr.com/3db8cb976d5a4c8b1de2a86b5e4839fa/tumblr_neh8jrvw3r1s77ypjo3_500.png "Score View")
![Image 3](https://33.media.tumblr.com/774e82b5f8090163e51aaf305655da0f/tumblr_neh8jrvw3r1s77ypjo2_500.png "Score View")

##How To Use##

Blinking libraries based off of the following two repos:

* Blinking Library: [https://github.com/thorikawa/EyeGestureLib/issues/2#issuecomment-61172704]
* Modifications of Blinking Library: [https://github.com/thorikawa/EyeGestureLib/issues/2#issuecomment-61172704]

## Pulling the .apk ##
If for some reason you are unable to root the glass, you can pull the apk with the following code (assuming you have adb installed):

* first, find your package with: <code>adb shell pm list packages</code>
* then, discover the path to your package: <code>adb shell pm path com.airbnb.android</code>
* finally, pull the file onto your computer: <code>adb pull /data/app/com.airbnb.android-1.apk</code>

Currently stable with:

+ XE22
+ Android Studio v0.8.6

Questions? Contact:
 
* tylerlehrlich@gmail.com.
* calebashmoreadams@gmail.com