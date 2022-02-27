(ns dojo.design-pattern.01-command)

(defn execute
  [command & args]
  (apply command args))

(defn db-login [id pw])
(defn db-logout [id])

(execute db-login "django" "q1w2e3")
(execute db-logout "django")
