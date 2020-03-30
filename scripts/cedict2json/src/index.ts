import fs from "fs"
// @ts-ignore
import cedictLookup from "cedict-lookup"

interface InputWord {
  traditional: string
  simplified: string
  pinyin: string
  english: string
}

interface OutputWord {
  id: string
  english: string
  chinese: string
  pinyin: string
  isFavorite: false
}

const CEDICT_FILE = "data/cedict_1_0_ts_utf-8_mdbg.txt"
const INPUT_FILE = "data/input"
const OUTPUT_FILE = "data/output.json"

const cedict = cedictLookup.loadSimplified(CEDICT_FILE)

function getDictEntry(zh: string): InputWord {
  const entry = cedict.getMatch(zh)[0]
  if (!entry) {
    throw Error(`No entry for ${zh}`)
  }
  return entry
}

function getWordsToProcess(): string[] {
  return fs
    .readFileSync(INPUT_FILE)
    .toString()
    .trim()
    .split("\n")
}

function inputWordToOutputWord(id: string, input: InputWord): OutputWord {
  return {
    id,
    english: input.english,
    chinese: input.simplified,
    pinyin: input.pinyin,
    isFavorite: false,
  }
}

const output = getWordsToProcess()
  .map(getDictEntry)
  .map((entry, index) => inputWordToOutputWord(index.toString(), entry))

fs.writeFileSync(OUTPUT_FILE, JSON.stringify(output, null, 2))
