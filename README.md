# NadUiComponent

**NadUiComponent** is a lightweight and modular Android UI component library designed for building beautiful and modern interfaces. It includes ready-to-use layouts like gradient and glow stroke views with support for rounded corners, customizable stroke, and background rendering — perfect for cards, containers, and effects.

![Version](https://img.shields.io/github/v/tag/mj743/NadUiComponent?label=version&color=blue)
![Build](https://img.shields.io/github/actions/workflow/status/mj743/NadUiComponent/android.yml?label=build&logo=github&style=flat)
![License](https://img.shields.io/github/license/mj743/NadUiComponent?style=flat)
![Platform](https://img.shields.io/badge/platform-android-brightgreen?style=flat)

## ✨ Features

- `NadGradientConstraintLayout` & `NadGlowConstraintLayout`
- `NadGradientFrameLayout` & `NadGlowFrameLayout`
- `NadGradientView` & `NadGlowView`
- Stroke with gradient/glow effect
- Per-corner radius customization
- Background color handled inside custom view
- Modular base classes for easy extension
## 📦 Installation

Using **JitPack**:

### 1. Add JitPack to your root `settings.gradle`:
```groovy
dependencyResolutionManagement {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

### 2. Add NadUiComponent to your module's `build.gradle`:
```groovy
implementation 'com.github.mj743:NadUiComponent:1.1.0'
```
## 🛠️ Usage

```xml
<com.nad.ui.component.layout.NadGradientConstraintLayout
    android:layout_width="match_parent"
    android:layout_height="140dp"
    android:background="@color/your_background"
    app:nadStrokeWidth="2dp"
    app:nadCornerRadius="16dp"
    app:nadStrokeColor="@color/white"
    app:nadStrokeDirection="horizontal" />
```
## ☕ Donate

If you like this project and want to support the developer:

**[💖 Donate via PayPal](https://paypal.me/MuhamadJaelani?country.x=ID&locale.x=id_ID)**

---
## 📄 License

MIT License

Copyright (c) 2024 Muhamad Jaelani

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE O
