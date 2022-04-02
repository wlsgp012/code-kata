(ns dojo.design-pattern.21-decorator
  (:require [dojo.design-pattern.20-adapter :refer [attack-with-bow attack-with-sword block-with-shield]]))

(defn block-with-power-armor [])

(def galahad {:name "Galahad"
              :speed 1.0
              :hp 100
              :attack-bow-fn attack-with-bow
              :attack-sword-fn attack-with-sword
              :block-fn block-with-shield})

(defn make-knight-with-more-hp
  [knight]
  (update-in knight [:hp] + 50))

(defn make-knight-with-power-armor
  [knight]
  (update-in knight [:block-fn] (fn [block-fn]
                                  (fn []
                                    (block-fn)
                                    (block-with-power-armor)))))

;; create the knight
(def superknight (-> galahad
                     make-knight-with-power-armor
                     make-knight-with-more-hp))
