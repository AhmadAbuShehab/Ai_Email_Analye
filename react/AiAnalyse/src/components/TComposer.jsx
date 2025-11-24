


export default function TComposer() {
  return (
    <>
        <div class="card">
            <h1>Versandauftrag</h1>
            <form action="#" method="post" autocomplete="off">
                <div class="grid">
                    <div>
                        <label for="abholort">Abholort</label>
                        <input id="abholort" name="abholort" type="text" placeholder="z. B. Musterstraße 1, 12345 Stadt" required></input>
                    </div>

                    <div>
                        <label for="zustellort">Zustellort</label>
                        <input id="zustellort" name="zustellort" type="text" placeholder="z. B. Zielstraße 2, 54321 Stadt" required></input>
                    </div>

                    <div>
                        <label for="absender">Absender</label>
                        <input id="absender" name="absender" type="text" placeholder="Name / Firma" required></input>
                    </div>

                    <div>
                        <label for="empfaenger">Empfänger</label>
                        <input id="empfaenger" name="empfaenger" type="text" placeholder="Name / Firma" required></input>
                    </div>

                    <div>
                        <label for="abholzeit">Abholzeit</label>
                        <input id="abholzeit" name="abholzeit" type="datetime-local" required></input>
                    </div>

                    <div>
                        <label for="zustellzeit">Zustellzeit</label>
                        <input id="zustellzeit" name="zustellzeit" type="datetime-local"></input>
                    </div>

                    <div>
                        <label for="kundenreferenz">Kundenreferenz</label>
                        <input id="kundenreferenz" name="kundenreferenz" type="text" placeholder="Referenz / Bestellnummer"></input>
                    </div>

                    <div>
                        <label for="auftragsnummer">AuftragsNummer</label>
                        <input id="auftragsnummer" name="auftragsnummer" type="text" placeholder="Auftragsnummer" required></input>
                    </div>

                    <div class="full">
                        <label for="bemerkungen">Bemerkungen (optional)</label>
                        <textarea id="bemerkungen" name="bemerkungen" placeholder="Weitere Hinweise"></textarea>
                    </div>
                </div>

                <div class="actions">
                    <button type="reset" class="btn btn-ghost">Zurücksetzen</button>
                    <button type="submit" class="btn btn-primary">Senden</button>
                </div>

                <p class="hint"> Pflichtfelder sind mit required markiert.</p>
            </form>
        </div>
    </>
  )
}