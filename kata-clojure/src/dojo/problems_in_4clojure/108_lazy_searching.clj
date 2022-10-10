(ns dojo.problems-in-4clojure.108-lazy-searching)

(defn subs [[x & xs :as xss] [y & ys :as yss]]
  (cond
    (> x y) (subs xss ys)
    (> y x) (subs xs yss)
    (= x y) (cons x (lazy-seq (subs xs ys)))))

(defn sol [& vs]
  (case (count vs)
    1 (apply min (first vs))
    2 (first (subs (first vs) (second vs)))
    (apply sol (conj (drop 2 vs) (subs (first vs) (second vs))))))

(= 3 (sol [3 4 5]))
(= 4 (sol [1 2 3 4 5 6 7] [0.5 3/2 4 19]))
(= 64 (sol (map #(* % % %) (range))
           (filter #(zero? (bit-and % (dec %))) (range))
           (iterate inc 20)))
(= 7 (sol (range) (range 0 100 7/6) [2 3 5 7 11 13]))

;; others
(fn ls [& s]
  (let [firsts (map first s)]
    (if (apply = firsts)
      (first firsts)
      (recur (map (fn [a] (filter #(>= % (apply max firsts)) a)) s)))))
