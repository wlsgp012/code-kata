(ns dojo.problems-in-4clojure.128-recognize-playing-cards)

(def sol
  (fn [sr]
    (let [suit-map {\D :diamond \H :heart \C :club \S :spade}
          rank-map (into {"T" 8 "J" 9 "Q" 10 "K" 11 "A" 12} (map-indexed #(vector (str %2) %1) (range 2 10)))]
      {:suit (suit-map (first sr)) :rank (rank-map (str (last sr)))})))

(= {:suit :diamond :rank 10} (sol "DQ"))

(= {:suit :heart :rank 3} (sol "H5"))

(= {:suit :club :rank 12} (sol "CA"))

(= (range 13) (map (comp :rank sol str)
                   '[S2 S3 S4 S5 S6 S7
                     S8 S9 ST SJ SQ SK SA]))

;; others
(fn [[suit rank]]
  {:suit ({\D :diamond \H :heart \S :spade \C :club} suit)
   :rank ((zipmap "23456789TJQKA" (range)) rank)})

(fn rpc[h]
  {:suit  ({\D :diamond
            \H :heart
            \S :spade
            \C :club}
           (first h))
   :rank (.indexOf [\2 \3 \4 \5 \6 \7 \8 \9 \T \J \Q \K \A] (second h))})

(fn [[a b]]
  (let [s {\S :spade \H :heart \D :diamond \C :club}
        r {\T 8 \J 9 \Q 10 \K 11 \A 12}]
    {:suit (s a) :rank (or (r b) (- (int b) 50))}))
