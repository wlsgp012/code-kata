(ns dojo.hanoi.simple)

(defn hanoi2
  ([n] (hanoi2 n 1 3 2))
  ([n from to empty]
   (let [moving (str from ">>>" to)]
     (if (= n 1)
       (println moving)
       (doall
        [(hanoi2 (dec n) from empty to)
         (println moving)
         (hanoi2 (dec n) empty to from)])))))

(defn hanoi
  ([n] (hanoi n 1 3 2))
  ([n from to empty]
             (when (> n 0)
               (hanoi (dec n) from empty to)
               (println (str "Move " n " from " from " to " to))
               (hanoi (dec n) empty to from))))

(comment
  (hanoi 2))
