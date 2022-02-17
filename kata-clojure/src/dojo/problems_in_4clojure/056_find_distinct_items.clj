(ns dojo.problems-in-4clojure.056-find-distinct-items)
;; https://4clojure.oxal.org/#/problem/56

(defn sol [coll]
  (reduce #(if (some (partial = %2) %1)
             %1
             (conj %1 %2))
          []
          coll))

(= (sol [1 2 1 3 1 2 4]) [1 2 3 4])
(= (sol [:a :a :b :b :c :c]) [:a :b :c])
(= (sol '([2 4] [1 2] [1 3] [1 3])) '([2 4] [1 2] [1 3]))
(= (sol (range 50)) (range 50))

;; others
#(reduce
  (fn [c e] (if ((set c) e)
              c
              (conj c e)))
  []
  %)

(fn  [coll]
  (let [step (fn step [xs seen]
               (lazy-seq
                ((fn [[f :as xs] seen]
                   (when-let [s (seq xs)]
                     (if (contains? seen f)
                       (recur (rest s) seen)
                       (cons f (step (rest s) (conj seen f))))))
                 xs seen)))]
    (step coll #{})))
