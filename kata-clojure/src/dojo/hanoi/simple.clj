(ns dojo.hanoi.simple)

(defn hanoi
  ([n] (hanoi n 1 3 2))
  ([n from to empty]
   (let [moving (str from ">>>" to)]
     (if (= n 1)
       (println moving)
       (doall
        [(hanoi (dec n) from empty to)
         (println moving)
         (hanoi (dec n) empty to from)])))))

(comment
  (hanoi 3))
