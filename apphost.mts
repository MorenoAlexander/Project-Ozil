// Aspire TypeScript AppHost
// For more information, see: https://aspire.dev

import { createBuilder } from "./.aspire/modules/aspire.mjs"

const builder = await createBuilder()

const api = builder.addJavaApp("ozil-api", ".")

const frontend = builder
    .addViteApp('angular', './frontend', { runScriptName: 'start' })
    // .publishAsStaticWebsite({ apiPath: '/api', apiTarget: api })
    .withExternalHttpEndpoints();


const redis = await builder.addContainer("cache", "redis:latest");

await builder.build().run()
