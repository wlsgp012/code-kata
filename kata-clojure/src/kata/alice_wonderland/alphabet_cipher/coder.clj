(ns kata.alice-wonderland.alphabet-cipher.coder)
;; https://github.com/gigasquid/wonderland-clojure-katas/tree/master/alphabet-cipher

(defn trans
  ([c p] (trans c p +))
  ([c p direction]
   (let [a (int \a)
         v (vec (range a (inc (int \z))))
         distance (- (int p) a)
         current-pos (- (int c) a)]
     (char (nth v (mod (direction current-pos distance) (count v)))))))

(defn rtrans [c p]
  (trans c p -))

(defn coding [k m t]
  (apply str (map t m (cycle k))))


(defn encode [keyword message]
  (coding keyword message trans))

(defn decode [keyword message]
  (coding keyword message rtrans))

(defn decipher [cipher message]
  "decypherme")
