(ns dojo.problems-in-4clojure.090-cartesian-product)
;; https://4clojure.oxal.org/#/problem/90

(defn sol
  [a b]
  (set
   (for [x a y b]
     [x y])))

(= (sol #{"ace" "king" "queen"} #{"♠" "♥" "♦" "♣"})
   #{["ace"   "♠"] ["ace"   "♥"] ["ace"   "♦"] ["ace"   "♣"]
     ["king"  "♠"] ["king"  "♥"] ["king"  "♦"] ["king"  "♣"]
     ["queen" "♠"] ["queen" "♥"] ["queen" "♦"] ["queen" "♣"]})

(= (sol #{1 2 3} #{4 5})
   #{[1 4] [2 4] [3 4] [1 5] [2 5] [3 5]})

(= 300 (count (sol (into #{} (range 10))
                  (into #{} (range 30)))))


;; others

(fn cartesian-product
  [coll-1 coll-2]
  (into #{} (mapcat #(map (partial vector %) coll-2) coll-1)))
