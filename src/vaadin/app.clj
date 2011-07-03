(ns vaadin.app
  (:gen-class
   :extends com.vaadin.Application
   :name vaadin.App
   :init cjinit
   :state state)
  (:use [vaadin.core])
  (:import [com.vaadin.terminal Sizeable]
           [com.vaadin Application]
           [com.vaadin.ui
            Button
            Button$ClickEvent
            Button$ClickListener
            GridLayout
            HorizontalSplitPanel
            Label
            Panel
            Tree
            VerticalLayout
            VerticalSplitPanel
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

(defonce planets [["Mercury" []]
                  ["Venus" []]
                  ["Earth" ["The Moon"]]
                  ["Mars" ["Phobos" "Deimos"]]
                  ["Jupiter" ["Io" "Europe" "Ganymedes" "Callisto"]]
                  ["Saturn" ["Titan" "Tethys" "Dione" "Rhea" "Iapetus"]]
                  ["Uranus" ["Miranda" "Ariel" "Umbriel" "Titania" "Oberon"]]
                  ["Neptune" ["Triton" "Proteus" "Nereid" "Larissa"]]])

(defn add-moons [tree planet moons]
  (reduce (fn [tree moon]
            (doto tree
              (.addItem moon)
              (.setParent moon planet)
              (.setChildrenAllowed moon false)))
          tree
          moons))

(defn add-planet [tree [planet moons]]
  (.addItem tree planet)
  (if (empty? moons)
    (.setChildrenAllowed tree planet false)
    (do
      (add-moons tree planet moons)
      (.expandItemsRecursively tree planet)))
  tree)

(defn planet-tree []
  (let [tree (Tree. "The Planets and Major Moons")]
    (reduce add-planet
            tree
            planets)))


(defn -init [app]
    (let [layout  (VerticalLayout.)
          window  (main-window app "Tree Example" layout)
          panel (Panel. "Panel for split panel III")
          hsplit (HorizontalSplitPanel.)]
      (swap! (.state app) assoc :main-window window)
      (.setContent panel hsplit)
      (.setSplitPosition hsplit 20 Sizeable/UNITS_PERCENTAGE)
      (.setSizeFull layout)
      (.setFirstComponent hsplit (planet-tree))
      (.setSecondComponent hsplit (Label. "Hello wornderworld"))
      (.addComponent window panel)))



