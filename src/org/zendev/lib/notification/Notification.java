package org.zendev.lib.notification;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class Notification {
    private URL trayIconImage;

    private TrayIcon.MessageType messageType;
    private PopupMenu popupMenu;

    private String iconToolTip;
    private String caption;
    private String message;

    public TrayIcon.MessageType getMessageType() {
        return messageType;
    }

    public String getIconToolTip() {
        return iconToolTip;
    }

    public String getCaption() {
        return caption;
    }

    public String getMessage() {
        return message;
    }

    public PopupMenu getPopupMenu() {
        return popupMenu;
    }

    public URL getTrayIconImage() {
        return trayIconImage;
    }

    public void show() throws AWTException, MalformedURLException {
        SystemTray tray = SystemTray.getSystemTray();

        if (tray != null) {
            Image image = null;

            if (trayIconImage == null) {
                image = Toolkit.getDefaultToolkit().createImage(new URL("http://localhost"));
            } else {
                image = Toolkit.getDefaultToolkit().createImage(trayIconImage);
            }

            TrayIcon trayIcon = new TrayIcon(image, iconToolTip);
            trayIcon.setImageAutoSize(true);

            tray.add(trayIcon);

            if (popupMenu != null) {
                trayIcon.setPopupMenu(popupMenu);
            }

            new Thread(() -> {
                trayIcon.displayMessage(caption, message, messageType);

                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                tray.remove(trayIcon);
            }).start();
        }
    }

    private Notification(NotificationBuilder builder) {
        this.trayIconImage = builder.trayIconImage;

        this.caption = builder.caption;
        this.message = builder.message;
        this.messageType = builder.messageType;

        this.iconToolTip = builder.iconToolTip;
        this.popupMenu = builder.popupMenu;
    }

    public static class NotificationBuilder {
        private URL trayIconImage;

        private TrayIcon.MessageType messageType;
        private PopupMenu popupMenu;

        private String iconToolTip;
        private String caption;
        private String message;

        public NotificationBuilder(String caption, String message, TrayIcon.MessageType messageType) {
            this.caption = caption;
            this.message = message;
            this.messageType = messageType;
        }

        public NotificationBuilder setTrayIconImage(URL trayIconImage) {
            this.trayIconImage = trayIconImage;
            return this;
        }

        public NotificationBuilder setMessageType(TrayIcon.MessageType messageType) {
            this.messageType = messageType;
            return this;
        }

        public NotificationBuilder setIconToolTip(String iconToolTip) {
            this.iconToolTip = iconToolTip;
            return this;
        }

        public NotificationBuilder setCaption(String caption) {
            this.caption = caption;
            return this;
        }

        public NotificationBuilder setMessage(String message) {
            this.message = message;
            return this;
        }

        public NotificationBuilder setPopupMenu(PopupMenu popupMenu) {
            this.popupMenu = popupMenu;
            return this;
        }

        public TrayIcon.MessageType getMessageType() {
            return messageType;
        }

        public String getIconToolTip() {
            return iconToolTip;
        }

        public String getCaption() {
            return caption;
        }

        public String getMessage() {
            return message;
        }

        public PopupMenu getPopupMenu() {
            return popupMenu;
        }

        public URL getTrayIconImage() {
            return trayIconImage;
        }

        public Notification build() {
            return new Notification(this);
        }
    }
}
