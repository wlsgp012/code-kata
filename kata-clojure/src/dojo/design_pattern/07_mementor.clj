(ns dojo.design-pattern.07-mementor)

(def textbox (atom {}))

(defn init-textbox
  []
  (reset! textbox {:text ""
                   :color :BLACK
                   :width 100}))

(def memento (atom nil))

(defn type-text
  [text]
  (swap! textbox
         (fn [m] (update m :text (fn [s] (str s text))))))

(defn save
  []
  (reset! memento (:text @textbox)))

(defn restore
  []
  (swap! textbox assoc :text @memento))

(init-textbox)
(type-text "'Like a Virgin' ")
(type-text "it's not about this sensitive girl ")
(save)
(type-text "who meets nice fella")
(println @textbox)
;;crash
(init-textbox)
(restore)
(println @textbox)
