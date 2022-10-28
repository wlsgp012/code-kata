(ns dojo.problems-in-4clojure.132-intervals)

;; wrong answer
(defn sol2 [p v xs]
  (mapcat (fn [[a b]] (if (p a b) [a v b] [a b])) (map #(vector %1 %2) xs (rest xs))))

;; this function can't be applied lazy eval
(defn sol3 [p v xs]
  (when (seq xs)
    (reduce (fn [r x] (into r (if (p (last r) x) [v x] [x])))
           [(first xs)]
           (rest xs))))

(defn sol [p v [x y & xs :as col]]
  (cond
    (empty? col) []
    (and (not (nil? x)) (not (nil? y))) (lazy-seq (if (p x y)
                               (cons x (cons v (sol p v (cons y xs))))
                               (cons x (sol p v (cons y xs)))))

    (nil? y) [x]))

(= '(1 :less 6 :less 7 4 3) (sol < :less [1 6 7 4 3]))

(= '(2) (sol > :more [2]))

(= [0 1 :x 2 :x 3 :x 4]  (sol #(and (pos? %) (< % %2)) :x (range 5)))

(empty? (sol > :more ()))

(= [0 1 :same 1 2 3 :same 5 8 13 :same 21]
   (take 12 (->> [0 1]
                 (iterate (fn [[a b]] [b (+ a b)]))
                 (map first) ; fibonacci numbers
                 (sol (fn [a b] ; both even or both odd
                       (= (mod a 2) (mod b 2)))
                     :same))))

;; others
(fn [c v s]
  (mapcat (fn [[a b]] (if (and a b (c a b)) (list a v) (list a)))
          (partition-all 2 1 s)))

(fn [p k coll]
  (when-let [[x & xs] (seq coll)]
    (cons x (mapcat #(if (p % %2) [k %2] [%2]) coll xs))))

(fn ins [f v [h & t]]
  (cond
    (nil? h) []
    (nil? t) [h]
    :else (lazy-cat [h] (if (f h (first t)) [v]) (ins f v t))))
