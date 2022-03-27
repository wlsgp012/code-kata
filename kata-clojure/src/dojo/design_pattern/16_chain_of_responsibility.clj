(ns dojo.design-pattern.16-chain-of-responsibility)

(defn log-filter
  [msg]
  (println (str "logging" msg))
  msg)

(defn stats-filter
  [msg]
  (println (count msg))
  msg)

(defn profanity-filter
  [msg]
  (clojure.string/replace msg "fuck" "f*ck"))

(defn reject-filter
  [msg]
  (if (.startsWith msg "[A Profit NY]")
    msg))

(defn chain
  [msg]
  (some-> msg
          reject-filter
          log-filter
          stats-filter
          profanity-filter))

(chain "fuck")

(chain "[A Profit NY] fuck")
