# Notification

Small library for creating and show notifications in the system.

## Authors

- [@Mehdi Lavasani](https://github.com/zendevMehdi)


## Features

- Create notifications and show them in the system tray.
- Add custom icon for notification in the tray.

  
## Installation

You can get jar from release section or create new project and add src folder to the project.


## Usage/Examples

I created new notification and show good morning to you.

```java
package org.zendev.lib.notification;

import java.awt.*;
import java.io.File;
import java.net.MalformedURLException;

public class App {
    public static void main(String[] args) throws MalformedURLException, AWTException {
        var notification = new Notification.NotificationBuilder("Good morning", "Hello wake up and start coding", TrayIcon.MessageType.INFO)
                .setTrayIconImage(new File("D:\\wallpaper\\459878.jpg").toURL())
                .setIconToolTip("Nothing here")
                .build();

        notification.show();
    }
}
```

## Screenshots

![screenshots/gm.png](https://github.com/zendevMehdi/Notification/blob/ed10076a5251d67512846f8580b8da881214a0ae/screenshots/gm.png)

