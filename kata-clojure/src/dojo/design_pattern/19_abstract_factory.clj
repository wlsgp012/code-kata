(ns dojo.design-pattern.19-abstract-factory)

(defn level-factory [wall-fn back-fn enemy-fn])

(defn make-stone-wall [])
(defn make-plasma-wall [])

(defn make-earth-back [])
(defn make-stars-back [])

(defn make-worm-scout [])
(defn make-ufo-soldier [])

(def underground-levell-factory
  (partial level-factory
           make-stone-wall
           make-earth-back
           make-worm-scout))

(def space-levell-factory
  (partial level-factory
           make-plasma-wall
           make-stars-back
           make-ufo-soldier))
