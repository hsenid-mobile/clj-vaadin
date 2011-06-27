(ns vaadin.core
  (:import [com.vaadin Application]
           [com.vaadin.ui
            AbstractComponentContainer
            Button
            Button$ClickEvent
            Button$ClickListener
            Component
            GridLayout
            Label
            Window
            Window$CloseListener
            Window$Notification]))

(defn show-message [window caption description]
              (.showNotification window caption description Window$Notification/TYPE_HUMANIZED_MESSAGE))
(defn show-warning [window caption description]
              (.showNotification window caption description Window$Notification/TYPE_WARNING_MESSAGE))
(defn show-error [window caption description]
            (.showNotification window caption description Window$Notification/TYPE_ERROR_MESSAGE))
(defn show-tray-notification [window caption description]
                        (.showNotification window caption description Window$Notification/TYPE_TRAY_NOTIFICATION))

(defn add-components [^AbstractMethodError container & components]
  (doseq [component components]
    (.addComponent container component)))

(defn- app-close-listener [app]
  (reify Window$CloseListener
    (windowClose [this event]
      (.close app))))

(defn main-window [app title layout]
  (let [window (Window. title layout)]
    (.setSizeUndefined layout)
    (.setMainWindow app window)
    (.addListener window (app-close-listener app))
    window))

(defn button-click-listener [listener]
  (reify Button$ClickListener
    (buttonClick [this event]
      (listener event))))