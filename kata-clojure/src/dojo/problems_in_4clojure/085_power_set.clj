(ns dojo.problems-in-4clojure.085-power-set)
;; https://4clojure.oxal.org/#/problem/85

(defn sol
  ([s] (sol s 0 #{#{}}))
  ([s size result]
   (if (= size (count s))
     result
     (recur s (inc size)
            (into result
                  (for [x result
                        y s
                        :when (= size (count x))]
                    (conj x y)))))))

(= (sol #{1 :a}) #{#{1 :a} #{:a} #{} #{1}})

(= (sol #{}) #{#{}})

(= (sol #{1 2 3})
   #{#{} #{1} #{2} #{3} #{1 2} #{1 3} #{2 3} #{1 2 3}})

(= (count (sol (into #{} (range 10)))) 1024)

;; others
(fn [xs]
  (letfn [(powerset [[x & xs]]
            (if x
              (concat (map (partial cons x) (powerset xs)) (powerset xs))
              [[]]))]
    (->> (powerset (into [] xs))
         (map set)
         set)))

(defn power-set
  [s]
  (reduce #(into % (for [subset %] (conj subset %2))) #{#{}} s))
