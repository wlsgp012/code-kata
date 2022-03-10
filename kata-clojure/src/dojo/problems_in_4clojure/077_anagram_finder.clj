(ns dojo.problems-in-4clojure.077-anagram-finder)
;; https://4clojure.oxal.org/#/problem/77

(defn sol2
  [coll]
  (set (map set (filter #(> (count %) 1) (vals (group-by (comp clojure.string/join sort) coll))))))

(defn sol
  [coll]
  (->> coll
       (group-by (comp clojure.string/join sort))
       (vals)
       (filter #(> (count %) 1))
       (map set)
       (set)))

(= (sol ["meat" "mat" "team" "mate" "eat"])
   #{#{"meat" "team" "mate"}})

(= (sol ["veer" "lake" "item" "kale" "mite" "ever"])
   #{#{"veer" "ever"} #{"lake" "kale"} #{"mite" "item"}})


;; others

(fn [s]
  (set
   (for [[_ v] (group-by set s) :when (second v)]
     (set v))))

(fn [c]
  (->> (group-by frequencies c)
       (vals)
       (filter #(> (count %) 1))
       (map set)
       (set)))

#(->> %
      (group-by sort)
      vals
      (filter next)
      (map set)
      set)
