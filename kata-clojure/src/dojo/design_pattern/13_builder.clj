(ns dojo.design-pattern.13-builder)

(defn make-coffee
  [name amount water & {:keys [milk sugar cinnamon] :or {milk 0 sugar 0 cinnamon 0}}]
  {:pre [(not (empty? name))
         (> amount 0)
         (> water 0)]}
  )

(make-coffee "Royale Coffee" 15 100 :milk 10 :cinnamon 3)

;; for mutable

;; interface
(defprotocol IStringBuilder
  (append [this s])
  (to-string [this]))

;; implementation
(deftype ClojureStringBuilder [charray ^:volatile-mutable last-pos]
  IStringBuilder
  (append [this s]
    (let [cs (char-array s)]
      (doseq [i (range (count cs))]
        (aset charray (+ last-pos i) (aget cs i))))
    (set! last-pos (+ last-pos (count s))))
  (to-string [this] (apply str (take last-pos charray))))

;; clojure binding
(defn new-string-builder
  []
  (ClojureStringBuilder. (char-array 100) 0))

;; usage
(def sb (new-string-builder))
(append sb "Toby Wong")
(to-string sb)
(append sb " ")
(append sb "Toby Chung")
