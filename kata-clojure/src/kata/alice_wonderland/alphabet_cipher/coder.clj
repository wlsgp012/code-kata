(ns kata.alice-wonderland.alphabet-cipher.coder)
;; https://github.com/gigasquid/wonderland-clojure-katas/tree/master/alphabet-cipher

(defn alphabet-index-vector [start end]
  (vec (range (int start) (inc (int end)))))

(defn trans
  ([c p] (trans c p +))
  ([c p direction]
   (let [a (int \a)
         v (alphabet-index-vector \a \z)
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

(defn rcycle [s]
  (loop [length 1]
    (when (> (count s) length)
      (let [p (partition length s)]
        (if (every? #(= (first p) %) p)
          (apply str (first p))
          (recur (inc length)))))))

(defn decipher [cipher message]
  (let [v (alphabet-index-vector \a \z)]
    (rcycle (apply str
                   (map (fn [c m] (char (nth v (let [r (- (int c) (int m))] (if (neg? r) (+ r (count v)) r)))))
                        cipher message)))))
