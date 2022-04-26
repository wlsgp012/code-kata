(ns kata.alice-wonderland.alphabet-cipher.coder)
;; https://github.com/gigasquid/wonderland-clojure-katas/tree/master/alphabet-cipher

(defn transform [c p]
  (let [a (int \a)
        z (int \z)
        distance (- (int p) a)
        r (+ (int c) distance)]
    (char
     (if (> r z)
       (+ (- a 1) (- r z))
       r))))

(defn r-transform [c p]
  (let [a (int \a)
        z (int \z)
        distance (- (int p) a)
        r (- (int c) distance)]
    (char
     (if (> a r)
       (- (+ z 1) (- a r))
       r))))

(defn encode [keyword message]
  (apply str (map transform message (cycle keyword))))

(defn decode [keyword message]
  (apply str (map r-transform message (cycle keyword))))

(defn decipher [cipher message]
  "decypherme")
