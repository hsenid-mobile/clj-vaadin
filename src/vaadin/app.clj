(ns vaadin.app
  (:gen-class
   :extends com.vaadin.Application
   :name vaadin.App
   :init cjinit
   :state state)
  (:use [vaadin.core])
  (:import 
           [com.vaadin Application]
           [com.vaadin.ui
            Button
            Button$ClickEvent
            Button$ClickListener
            GridLayout
            Label
            Window]))

(defn -cjinit []
  [[] (atom {})])


(defn- calc-button-listener [^Label display app]
  (button-click-listener
   (fn [^Button$ClickEvent event]
     (let [button (.getButton event)
           op     (.charAt (.getCaption button) 0)
           {:keys [main-window]} @(.state app)]
       (.setValue display op)
       (show-tray-notification  main-window "Hello" "Yellow")))))

(def ops ["7" "8" "9" "/" "4" "5" "6" "*" "1" "2" "3" "-" "0" "=" "C" "+"])

(defn -init [app]
  (let [layout  (GridLayout. 4 5)
        window  (main-window app "Caclulator" layout)
        display (Label. "11")]
    (swap! (.state app) assoc :main-window window)
    (.addComponent layout display 0 0 3 0)
    (doseq [btn (map #(Button. %) ops )]
      (.addListener btn (calc-button-listener display app) )
      (.addComponent layout btn))))


