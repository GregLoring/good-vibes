
# Clojure Training

## Minimal Environment Setup

VS Code on WSL: Ubuntu-20.04

Use [SDKMAN!](https://sdkman.io/) for java and leiningen.

```bash
sdk install java
sdk install leiningen
```

Extensions:

- Calva
- macros (by geddski)

Check that `lein` is on VS Code's path. "I had to do this: <https://code.visualstudio.com/docs/setup/mac> and then start VS Code by using `code` command." - NickM

## Thinking in Calva keyboard shortcut combos

`ctrl+shift-p` is the VS Code keybinding for **Show All Commands**. Therefore, a lot of docs have you start there so that you can find the command they want you to use.

The Calva commands are often combos of two keystrokes, where the first keystroke of the combo is `ctrl+alt+c`.  So, I tend to think of these combos as `ctrl+alt+c` **Calva** followed by `keybinding` *Calva command*.

For example, `ctrl+alt+c ctrl+alt+j` is the hot-key combo for **Start a Project REPL and Connect**.  Putting it all together, my brain is thinking `ctrl-alt-c` **Calva** `ctrl-alt-j` **Jack-in**

## Detailed Setup

; prettifier (formatter)

In project.clj,

```clojure
 :plugins [[lein-cljfmt "0.6.4"] ...
```

```bash
$ git ls-files -mo | egrep -v '^target/' | egrep '.clj$'
… returns new and modified clojure files. So,
$ git ls-files -mo | egrep -v '^target/' | egrep '.clj$' | xargs lein cljfmt check
```

; :user profile - ~/.lein/profiles.clj

```clojure
{:other {:jvm-opts ["-DDB_HOST=…"]}}
```

settings.json: shift+cmd+p > Preferences: Open Settings (JSON)

```json
  "calva.showDocstringInParameterHelp": true,
  "calva.syncReplNamespaceToCurrentFile": true,
  "calva.paredit.defaultKeyMap": "original",
  "calva.fmt.alignMapItems": true,
  "calva.replConnectSequences": [
    {
      "name": "Leiningen clj-only (oscar, ueshell)",
      "projectType": "Leiningen",
      "cljsType": "none",
      "menuSelections": {
         "leinAlias": null,
         "leinProfiles": [":dev", ":oscar"]
       }
     }
  ],

  "macros": {
    "calvaRebuild": [
      "saveAll",
      "calva.refresh",
      "calva.loadNamespace"
    ]
  }
```

calvaRebuild does everything necessary to update the REPL. You may need to do this twice because file order is not deterministic and if you change a function name or something, dependent files may fail the first time.

keybindings.json: shift+cmd+p > Preferences: Open Keyboard Shortcuts (JSON)

```json
  {
    "key": "cmd+y",
    "command": "default:redo"
  },
  {
    "key": "cmd+;",
    "command": "editor.action.commentLine",
    "when": "editorTextFocus && !editorReadonly"
  },
  {
    "key": "ctrl+;",
    "command": "editor.action.commentLine",
    "when": "editorTextFocus && !editorReadonly"
  },
  {
    "key": "ctrl+alt+c ctrl+alt+k",
    "command": "calva.disconnect"
  },
  {
    "key": "ctrl+alt+c ctrl+alt+r",
    "command": "macros.calvaRebuild"
  }
```

Think of `ctrl+alt+c ctrl+alt+k` as “kill REPL”.

; linter

.clj-kondo/config.edn

```clojure
{:linters {:refer-all {:level :off}}}
```

Update .gitignore to suppress files create by linter: `.clj-kondo/.cache/*/lock`.

Update .gitignore to suppress files created by Calva “Run … tests” commands: `*.transit.json`.
